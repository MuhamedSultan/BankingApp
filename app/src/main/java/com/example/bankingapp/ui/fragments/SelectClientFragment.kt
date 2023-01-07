package com.example.bankingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankingapp.R
import com.example.bankingapp.adapter.SelectClientAdapter
import com.example.bankingapp.databinding.FragmentSelectClientBinding
import com.example.bankingapp.models.Transfer
import com.example.bankingapp.viewmodel.ClientsViewModel


class SelectClientFragment : Fragment() {
    private lateinit var binding: FragmentSelectClientBinding
    private lateinit var viewModel: ClientsViewModel
    private val args by navArgs<SelectClientFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSelectClientBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ClientsViewModel::class.java]
        val adapter = SelectClientAdapter()
        binding.selectClientRecycler.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.getSelectedClients(args.toClient.name.orEmpty())
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it)
            })
        adapter.setOnItemClickListener { selectedClient ->
            val fromClient = args.toClient
            val transferAmount = args.transferAmount

            val newTransfer = Transfer(
                fromClient.name.orEmpty(),
                selectedClient.name!!,
                transferAmount.toString()
            )
            viewModel.addNewTransfer(newTransfer)

            // Update fromClient
            fromClient.currentBalance?.toIntOrNull()?.let { fromClientBalance ->
                val updatedFromClientBalance = fromClientBalance - transferAmount
                fromClient.currentBalance = updatedFromClientBalance.toString()
            }
            viewModel.updateClient(fromClient)

            // Update toClient
            selectedClient.currentBalance?.toIntOrNull()?.let { selectedClientBalance ->
                val updatedSelectedClientBalance = selectedClientBalance + transferAmount
                selectedClient.currentBalance = updatedSelectedClientBalance.toString()
            }
            viewModel.updateClient(selectedClient)

            Toast.makeText(requireContext(), "Successful Transfer", Toast.LENGTH_LONG).show()

            findNavController().popBackStack(R.id.clientsragment, true)
        }

        return binding.root
    }


}