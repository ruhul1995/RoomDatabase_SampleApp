package com.example.roomdatabase_sampleapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabase_sampleapp.model.User

// UserDao will contain methods to access database

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if there is same user,then we will ignore it
    suspend fun addUser(user: User) // suspend is added as we will use coroutine

    @Update
    suspend fun updateUser(user: User)

    //deletes single user
    @Delete
    suspend fun deleteUser(user: User)

    //custom query to delete all user
    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>
}