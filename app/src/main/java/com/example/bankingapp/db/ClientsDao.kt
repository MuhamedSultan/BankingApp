package com.example.bankingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bankingapp.models.Clients
import com.example.bankingapp.models.Transfer

@Dao
interface ClientsDao {

    @Insert
    suspend fun addClient(clients: kotlin.collections.List<com.example.bankingapp.models.Clients>)

    @Query("select * from clients_table order by id asc")
    fun getAllClients(): LiveData<List<Clients>>

    @Insert
    suspend fun addNewTransfer(transfer: Transfer)

    @Query("select * from transfer_table order by id asc")
    fun getAllTransfers(): LiveData<List<Transfer>>

    @Query("select * from clients_table where name is not :clientName")
    fun getSelectedClients(clientName: String) :LiveData<List<Clients>>

    @Update
    suspend fun updatClient(clients: Clients)

}