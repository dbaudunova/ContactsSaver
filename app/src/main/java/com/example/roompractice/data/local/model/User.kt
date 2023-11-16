package com.example.roompractice.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var age: Int? = null,
    @Embedded
    var address: Address? = null
): Serializable

data class Address(
    var streetName: String? = null,
    var homeNumber: Int? = null
)
