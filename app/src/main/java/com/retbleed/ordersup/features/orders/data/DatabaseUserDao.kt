package com.retbleed.ordersup.features.orders.data

import androidx.room.*

@Dao
interface DatabaseUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: DatabaseUser)

    @Query("SELECT * FROM DatabaseUser")
    suspend fun getAllUsers(): List<DatabaseUser>

    @Query("SELECT * FROM DatabaseUser WHERE uid = :uid")
    suspend fun getUserById(uid: String): DatabaseUser?

    @Update
    suspend fun updateUser(user: DatabaseUser)

    @Delete
    suspend fun deleteUser(user: DatabaseUser)
}
