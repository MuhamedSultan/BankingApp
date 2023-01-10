package com.example.bankingapp.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bankingapp.R
import com.example.bankingapp.databinding.FragmentInfoBinding
import com.example.bankingapp.viewmodel.ClientsViewModel
import kotlinx.android.synthetic.main.transfer_money_dialouge.view.*


class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var viewModel: ClientsViewModel
    private val args by navArgs<InfoFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ClientsViewModel::class.java]
        args.currentClient.name.also { binding.tvName.text = it }
        "Email: ${args.currentClient.email}".also { binding.tvEmail.text = it }
        "Balance:${args.currentClient.currentBalance}".also { binding.tvBalance.text = it }
        "Phone: ${args.currentClient.phone}".also { binding.tvPhone.text = it }
        "AccountNumber: ${args.currentClient.accountNumber}".also {
            binding.tvAccountNumber.text = it
        }
        binding.transferMoney.setOnClickListener {
            showDialogue()
        }

        return binding.root
    }

    private fun showDialogue() {
        val alertbuilder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.transfer_money_dialouge, null)
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
            val transferAmount = view.ed_transfer.text.toString().toIntOrNull() ?: 0
            if (transferAmount.toString().toInt() > args.currentClient.currentBalance.toString()
                    .toInt()
            ) {
                Toast.makeText(requireContext(), "Your balance Not enough", Toast.LENGTH_LONG)
                    .show()
            } else {
                alertDialog.dismiss()
                // val transferAmount = view.ed_transfer.text.toString().toIntOrNull() ?: 0

                // Toast.makeText(requireActivity(),"Successful Transfer",Toast.LENGTH_LONG).show()
                findNavController().navigate(
                    InfoFragmentDirections.actionInfoFragmentToSelectClientFragment2(
                        args.currentClient,
                        transferAmount
                    )
                )
            }

        }
    }
}