package com.retbleed.ordersup.features.orders.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseUser(
    @PrimaryKey val uid: String,
    val name: String,
    val password: String,
    val whereOrigin: String
)