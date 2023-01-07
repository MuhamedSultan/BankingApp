package com.example.bankingapp.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
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
import com.example.bankingapp.R
import com.example.bankingapp.databinding.FragmentInfoBinding
import com.example.bankingapp.models.Clients
import com.example.bankingapp.models.Transfer
import com.example.bankingapp.viewmodel.ClientsViewModel
import kotlinx.android.synthetic.main.transfer_money_dialouge.view.*


class InfoFragment : Fragment() {

    private lateinit var binding : FragmentInfoBinding
    private lateinit var viewModel: ClientsViewModel
    private val args by navArgs<InfoFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentInfoBinding.inflate(layoutInflater)
        viewModel=ViewModelProvider(this)[ClientsViewModel::class.java]
        args.currentClient.name.also { binding.tvName.text = it }
        "Email: ${args.currentClient.email}".also { binding.tvEmail.text = it }
        "Balance:${args.currentClient.currentBalance}".also { binding.tvBalance.text = it }
        "Phone: ${args.currentClient.phone}".also { binding.tvPhone.text = it }
        "AccountNumber: ${args.currentClient.accountNumber}".also { binding.tvAccountNumber.text=it }
        binding.transferMoney.setOnClickListener {
            showDialogue()
        }

        return binding.root
    }

    private fun showDialogue() {
        val alertbuilder = AlertDialog.Builder(requireContext())
        val view =layoutInflater.inflate(R.layout.transfer_money_dialouge,null)
        alertbuilder.setView(view)
        val alertDialog = alertbuilder.create()
        alertDialog.show()
        alertDialog.setCancelable(false)
        view.cancel.setOnClickListener {
            alertDialog.dismiss()
        }
        view.confirm.setOnClickListener {
           // val newTransfer=Transfer(args.currentClient.name,view.ed_transfer.text.toString())
         //   viewModel.addNewTransfer(newTransfer)
            alertDialog.dismiss()

            val sharedPreferences = requireContext().getSharedPreferences("DataOfUser", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
           editor.putString("fromClient",args.currentClient.name)
            editor.putString("fromClientBalance",view.ed_transfer.text.toString())
            editor.apply()
           // Toast.makeText(requireActivity(),"Successful Transfer",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_infoFragment_to_selectClientFragment2)
        }
    }
}
