package com.example.bankingapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "transfer_table")
data class Transfer(
    var transferFrom: String?=null,
    var transferTo: String,
    var amount:String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null
}
