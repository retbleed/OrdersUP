package com.retbleed.ordersup.features.orders.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseProduct(
    @PrimaryKey val uid: String
)