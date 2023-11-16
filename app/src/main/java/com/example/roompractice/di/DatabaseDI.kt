package com.example.roompractice.di

import android.content.Context
import androidx.room.Room
import com.example.roompractice.data.local.room.UserDatabase

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideDao(db: UserDatabase) = db.userDao()