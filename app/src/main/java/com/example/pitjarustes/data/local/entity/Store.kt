package com.example.pitjarustes.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storeTable")
data class Store (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "store_id")
    val id : Int?,

    @ColumnInfo(name = "store_name")
    val name : String?,

    @ColumnInfo(name = "store_cluster")
    val cluster : String?,

    @ColumnInfo(name = "store_display")
    val display : String?,

    @ColumnInfo(name = "store_subdisplay")
    val subdisplay : String?,

    @ColumnInfo(name = "store_status")
    val status : String?,

    @ColumnInfo(name = "store_alamat")
    val alamat : String?,

    @ColumnInfo(name = "store_ertm")
    val ertm : Boolean?,

    @ColumnInfo(name = "store_pareto")
    val pareto : Boolean?,

    @ColumnInfo(name = "store_merchandise")
    val merchandise : Boolean?,

    @ColumnInfo(name = "store_image")
    val image : Int?,

    @ColumnInfo(name = "store_last_visit")
    val lastVisit : String?,

    @ColumnInfo(name = "store_longitude")
    val longitude : Double?,

    @ColumnInfo(name = "store_latitude")
    val latitude : Double?,
)