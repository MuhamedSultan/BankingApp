package com.example.bankingapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
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
    val getAllTransfers: LiveData<List<Transfer>>


    init {
        val clientsDao = ClientsDatabase.getInstance(application)!!.clientDao()
        repository = ClientsRepository(clientsDao)
        getAllClients = repository.getAllClients
        getAllTransfers = repository.getAllTransfers

    }

    fun addClient(clients: List<Clients>) = viewModelScope.launch(Dispatchers.IO)
    {

            repository.addClient(clients)


    }

    fun addNewTransfer(transfer: Transfer) = viewModelScope.launch {
        repository.addNewTransfer(transfer)
    }

    fun updateClient(clients: Clients) = viewModelScope.launch {
        repository.updateClient(clients)
    }


    fun getSelectedClients(clientName: String): LiveData<List<Clients>> {
        return repository.getSelectedClients(clientName)

    }

      fun setUpFirstTime(context: Context) {
        val shared = context.getSharedPreferences("FirstTime", AppCompatActivity.MODE_PRIVATE)
        if (shared.getBoolean("check", true)) {
               addClient(clientsData())
            val editor = shared.edit()
            editor.putBoolean("check", false)
            editor.apply()
        }
    }
    private fun clientsData(): List<Clients> {
       return listOf(
            Clients(
                "Muhamed", "Muhamed@gmail.com", "50000",
                "01023459672", "123654"
            ), Clients(
                "Mahmoud", "Mahmoud@gmail.com", "70000",
                "01223959175", "143844"
            ), Clients(
                "Khaled", "Khaled@gmail.com", "30000",
                "01286959815", "141854"
            ), Clients(
                "Ali", "Ali@gmail.com", "10000",
                "01185929810", "543850"
            ), Clients(
                "Sameh", "Sameh@gmail.com", "80000",
                "01585917830", "243731"
            ), Clients(
                "Hazim", "Hazem@gmail.com", "90000",
                "01184327830", "290739"
            ), Clients(
                "Ebrahim", "Ebrahim@gmail.com", "50000",
                "01284915633", "569834"
            ), Clients(
                "Karim", "Karim@gmail.com", "20000",
                "01550439220", "158736"
            ), Clients(
                "Yousef", "Yousef@gmail.com", "60000",
                "01086812323", "142368"
            ), Clients(
                "Ahmed", "Ahmed@gmail.com", "50000",
                "0100050342902", "753439"
            )
        )

    }
}
