package com.assignment.countrylist.model
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class DataModels (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val rows:List<Row>?=null)