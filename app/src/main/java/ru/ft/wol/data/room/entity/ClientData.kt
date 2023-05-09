package ru.ft.wol.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClientData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String,
    val macAddress: String
)