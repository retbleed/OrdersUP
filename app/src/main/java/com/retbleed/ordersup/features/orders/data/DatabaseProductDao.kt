package com.retbleed.ordersup.features.orders.data

import androidx.room.*

@Dao
interface DatabaseProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: DatabaseProduct)

    @Query("SELECT * FROM DatabaseProduct")
    suspend fun getAllProducts(): List<DatabaseProduct>

    @Query("SELECT * FROM DatabaseProduct WHERE uid = :uid")
    suspend fun getProductById(uid: String): DatabaseProduct?

    @Update
    suspend fun updateProduct(product: DatabaseProduct)

    @Delete
    suspend fun deleteProduct(product: DatabaseProduct)
}
