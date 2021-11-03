package com.example.roomdatabase_sampleapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase_sampleapp.model.User

//This class contains the database holder and serves as the main acesss point for underlying connection
//to our app's persisted,relation data

@Database(entities = [User::class], version = 1,  exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao():UserDao

    // to make our userdata singleton class so that instance is only one
    companion object{
        @Volatile //  writes to this field are immediately made visible to other threads.
        private var INSTANCE : UserDatabase? = null

        fun getDatabase(context: Context) : UserDatabase{
            val tempInstance  = INSTANCE
            if (tempInstance!= null)
            {
                return tempInstance
            }

            //creating new instance if our instance is null
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }

}