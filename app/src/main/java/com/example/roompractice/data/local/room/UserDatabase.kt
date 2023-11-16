package com.example.roompractice.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roompractice.data.local.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}