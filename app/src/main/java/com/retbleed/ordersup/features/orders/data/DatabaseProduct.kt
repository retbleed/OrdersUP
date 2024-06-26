package com.retbleed.ordersup.features.orders.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseProduct(
    @PrimaryKey val uid: String,
    val name: String,
    val image64: String,
    val createdBy: Int,
    val createdAt: String,
    val whereOrigin: String
)