package com.example.bankingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bankingapp.models.Clients
import com.example.bankingapp.models.Transfer


@Database(entities = [Clients::class,Transfer::class], version =1)

abstract class ClientsDatabase : RoomDatabase() {

    abstract fun clientDao(): ClientsDao

    companion object {
        @Volatile
         var instance: ClientsDatabase? = null
        @Synchronized
        fun getInstance(context: Context): ClientsDatabase? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClientsDatabase::class.java,
                    "clients_database"
                ).fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }
}