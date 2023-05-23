package com.example.pitjarustes.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id : Int?,

    @ColumnInfo(name = "username")
    val username : String?,

    @ColumnInfo(name = "user_password")
    val password : String?,

    @ColumnInfo(name = "user_fullname")
    val fullname : String?,

    @ColumnInfo(name = "user_role")
    val role : String?,

    @ColumnInfo(name = "user_nik")
    val nik : String?,

    @ColumnInfo(name = "user_photo")
    val photo : Int?,
)