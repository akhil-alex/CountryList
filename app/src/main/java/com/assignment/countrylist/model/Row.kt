package com.assignment.countrylist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Row (
    var description:String?=null,
    var imageHref:String?=null,
    var title:String?=null,
    @PrimaryKey(autoGenerate = true)
    val id:Int

)