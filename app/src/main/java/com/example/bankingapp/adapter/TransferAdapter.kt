package com.example.bankingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bankingapp.databinding.ItemViewTransferBinding
import com.example.bankingapp.models.Clients
import com.example.bankingapp.models.Transfer

class TransferAdapter : RecyclerView.Adapter<TransferAdapter.TransferViewHolder>() {
    var transferList = emptyList<Transfer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferViewHolder {
        return TransferViewHolder(
            ItemViewTransferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TransferViewHolder, position: Int) {
        val itemTransferList=transferList[position]
        "From: ${itemTransferList.transferFrom}".also { holder.transferFrom.text=it }
        "To: ${itemTransferList.transferTo}".also { holder.transferTo.text = it }
       "Amount: ${itemTransferList.amount}".also { holder.amount.text=it }
    }

    override fun getItemCount(): Int {
       return transferList.size
    }
    fun setData(list:List<Transfer>){
        this.transferList=list
        notifyDataSetChanged()
    }

    inner class TransferViewHolder(binding: ItemViewTransferBinding) : ViewHolder(binding.root) {
        val transferFrom=binding.textView2
        val transferTo = binding.transferTo
        val amount = binding.amount

    }
}