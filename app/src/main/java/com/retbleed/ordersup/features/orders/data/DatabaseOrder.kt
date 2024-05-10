package com.retbleed.ordersup.features.orders.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseOrder(
    @PrimaryKey val uid: String
)