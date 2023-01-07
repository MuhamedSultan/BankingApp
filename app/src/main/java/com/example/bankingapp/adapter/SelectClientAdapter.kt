package com.example.bankingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankingapp.databinding.ItemViewClientsBinding
import com.example.bankingapp.models.Clients

class SelectClientAdapter : RecyclerView.Adapter<SelectClientAdapter.SelectClientViewHolder>() {

    var clientList = emptyList<Clients>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectClientViewHolder {
        return SelectClientViewHolder(
            ItemViewClientsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    private var onItemClickListener: ((Clients) -> Unit)? = null
    override fun onBindViewHolder(holder: SelectClientViewHolder, position: Int) {

        val clientList = clientList[position]
        holder.clientName.text = clientList.name
        holder.clientBalance.text = clientList.currentBalance

          holder.itemViewClient.setOnClickListener {
                onItemClickListener?.let { it(clientList) }
            }
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    fun setData(list: List<Clients>) {
        this.clientList = list
        notifyDataSetChanged()
    }

    inner class SelectClientViewHolder(binding: ItemViewClientsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemViewClient=binding.itemViewClient
        val clientName = binding.tvClientName
        val clientBalance = binding.tvClientBalance

    }

    fun setOnItemClickListener(listener: (Clients) -> Unit) {
        onItemClickListener = listener
    }
}