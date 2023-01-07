package com.example.bankingapp.repository

import androidx.lifecycle.LiveData
import com.example.bankingapp.db.ClientsDao
import com.example.bankingapp.models.Clients
import com.example.bankingapp.models.Transfer

class ClientsRepository(private val clientsDao: ClientsDao) {

    suspend fun addClient(clients: List<Clients>){
        clientsDao.addClient(clients)
    }

    val getAllClients : LiveData<List<Clients>> = clientsDao.getAllClients()

     fun getSelectedClients(clientName:String) :LiveData<List<Clients>>{
       return clientsDao.getSelectedClients(clientName)
    }

    suspend fun addNewTransfer(transfer: Transfer){
        clientsDao.addNewTransfer(transfer)
    }
    val getAllTransfers : LiveData<List<Transfer>> = clientsDao.getAllTransfers()

    suspend fun updateClient(clients: Clients){
        clientsDao.updatClient(clients)
    }
}