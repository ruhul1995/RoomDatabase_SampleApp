package com.example.roomdatabase_sampleapp.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabase_sampleapp.data.UserDao
import com.example.roomdatabase_sampleapp.model.User

// This class abstracts access to multiple data sources
//it is suggested for good practice for code seperation and architecture

class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUsers()
    }
}