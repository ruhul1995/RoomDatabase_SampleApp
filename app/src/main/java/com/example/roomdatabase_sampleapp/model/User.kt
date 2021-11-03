package com.example.roomdatabase_sampleapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize

//Entity is used to create table
@Parcelize
@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val firstName :String,
    val lastName :String,
    val age:Int
):Parcelable  // when we make our data class parcelable, we should be able to pass our user object