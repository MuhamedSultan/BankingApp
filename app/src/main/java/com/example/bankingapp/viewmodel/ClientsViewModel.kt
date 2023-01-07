package com.example.bankingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bankingapp.db.ClientsDatabase
import com.example.bankingapp.models.Clients
import com.example.bankingapp.models.Transfer
import com.example.bankingapp.repository.ClientsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ClientsRepository
    val getAllClients: LiveData<List<Clients>>
    val getAllTransfers : LiveData<List<Transfer>>


    init {
        val clientsDao = ClientsDatabase.getInstance(application)!!.clientDao()
        repository = ClientsRepository(clientsDao)
        getAllClients = repository.getAllClients
        getAllTransfers = repository.getAllTransfers

    }

    fun addClient(clients: List<Clients>) = viewModelScope.launch(Dispatchers.IO) {
        repository.addClient(clients)
    }
    fun addNewTransfer(transfer: Transfer)=viewModelScope.launch {
        repository.addNewTransfer(transfer)
    }
    fun updateClient(clients: Clients)=viewModelScope.launch {
        repository.updateClient(clients)
    }


     fun getSelectedClients(clientName:String) : LiveData<List<Clients>>{
      return repository.getSelectedClients(clientName)

    }
}