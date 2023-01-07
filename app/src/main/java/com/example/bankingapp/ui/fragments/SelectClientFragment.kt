package com.example.bankingapp.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankingapp.R
import com.example.bankingapp.adapter.SelectClientAdapter
import com.example.bankingapp.databinding.FragmentSelectClientBinding
import com.example.bankingapp.models.Clients
import com.example.bankingapp.models.Transfer
import com.example.bankingapp.viewmodel.ClientsViewModel


class SelectClientFragment : Fragment() {
private lateinit var binding:FragmentSelectClientBinding
private lateinit var viewModel: ClientsViewModel
private lateinit var  sharedPreferences: SharedPreferences
private lateinit var  fromClient :String
private val args by navArgs<SelectClientFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentSelectClientBinding.inflate(layoutInflater)
        viewModel=ViewModelProvider(this)[ClientsViewModel::class.java]
        val adapter =SelectClientAdapter()
        binding.selectClientRecycler.let {
            it.adapter=adapter
            it.layoutManager=LinearLayoutManager(requireContext())
        }
        sharedPreferences =
            requireContext().getSharedPreferences("DataOfUser", Context.MODE_PRIVATE)
         fromClient = sharedPreferences.getString("fromClient", "Nothing").toString()
       viewModel.getSelectedClients(fromClient).observe(viewLifecycleOwner, Observer {
           adapter.setData(it)
       })
        adapter.setOnItemClickListener {
             sharedPreferences =
               requireContext().getSharedPreferences("DataOfUser", Context.MODE_PRIVATE)

             fromClient = sharedPreferences.getString("fromClient", "Nothing").toString()
            val balance = sharedPreferences.getString("fromClientBalance", "Nothing")

            val newTransfer=Transfer(fromClient,it.name!!,balance.toString())
            viewModel.addNewTransfer(newTransfer)
            Toast.makeText(requireContext(),"Successful Transfer",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_selectClientFragment_to_clientsragment2)
           // val bb= args.client.currentBalance + balance
           // val update=Clients(null,null,bb,null,null)
          //  viewModel.updateClient(update)
        }

        return binding.root
    }


}