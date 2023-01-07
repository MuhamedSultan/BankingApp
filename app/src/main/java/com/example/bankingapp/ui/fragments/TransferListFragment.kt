package com.example.bankingapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankingapp.adapter.TransferAdapter
import com.example.bankingapp.databinding.FragmentTransferListBinding
import com.example.bankingapp.viewmodel.ClientsViewModel

class TransferListFragment : Fragment() {
   private lateinit var binding: FragmentTransferListBinding
   private lateinit var viewModel:ClientsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentTransferListBinding.inflate(layoutInflater)
        viewModel=ViewModelProvider(this)[ClientsViewModel::class.java]
        val transferAdapter=TransferAdapter()
        binding.transferRecyclerView.let {
            it.adapter=transferAdapter
            it.layoutManager=LinearLayoutManager(context)
        }
        viewModel.getAllTransfers.observe(viewLifecycleOwner, Observer {
            transferAdapter.setData(it)
        })

        return binding.root
    }
}