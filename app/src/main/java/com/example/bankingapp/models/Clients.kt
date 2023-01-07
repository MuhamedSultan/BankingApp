package com.example.bankingapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "clients_table")
@Parcelize
data class Clients(
    var name: String?=null,
    var email: String?=null,
    var currentBalance: String?=null,
    var phone: String?=null,
    var accountNumber: String?=null
) :Parcelable{
    @PrimaryKey(autoGenerate = true)
    var  id: Int? =null
}
