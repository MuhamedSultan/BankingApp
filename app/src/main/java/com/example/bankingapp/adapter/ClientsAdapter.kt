package com.example.bankingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bankingapp.databinding.ItemViewClientsBinding
import com.example.bankingapp.models.Clients
import com.example.bankingapp.ui.fragments.ClientsFragmentDirections

class ClientsAdapter : RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder>() {

    private var clientList = emptyList<Clients>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder {
        return ClientsViewHolder(
            ItemViewClientsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int) {
        val clientList = clientList[position]
        holder.clientName.text = clientList.name
        holder.clientBalance.text = clientList.currentBalance
        holder.itemView.setOnClickListener {
            val action = ClientsFragmentDirections.actionClientsragmentToInfoFragment(clientList)
            holder.itemView.findNavController()
                .navigate((action))
        }
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    fun setData(list: List<Clients>) {
        this.clientList = list
        notifyDataSetChanged()
    }

    inner class ClientsViewHolder(binding: ItemViewClientsBinding) : ViewHolder(binding.root) {
        val clientName = binding.tvClientName
        val clientBalance = binding.tvClientBalance
    }


}