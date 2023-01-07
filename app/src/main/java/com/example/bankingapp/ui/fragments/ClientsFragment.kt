package com.example.bankingapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankingapp.adapter.ClientsAdapter
import com.example.bankingapp.databinding.FragmentClientsragmentBinding
import com.example.bankingapp.viewmodel.ClientsViewModel

class ClientsFragment : Fragment() {

 lateinit var binding: FragmentClientsragmentBinding
 private lateinit var viewModel : ClientsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentClientsragmentBinding.inflate(layoutInflater)
        viewModel=ViewModelProvider(this)[ClientsViewModel::class.java]

        val adapter = ClientsAdapter()
        binding.clientsRecycler.let {
           it.adapter=adapter
            it.layoutManager=LinearLayoutManager(requireContext())
        }


        viewModel.getAllClients.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        return binding.root
    }


}