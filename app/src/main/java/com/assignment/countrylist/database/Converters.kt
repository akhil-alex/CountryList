package com.assignment.countrylist.database
import androidx.room.TypeConverter
import com.assignment.countrylist.model.Row
import com.google.gson.Gson
class Converters {
    @TypeConverter
    fun listToJson(value:List<Row>?)= Gson().toJson(value)
    @TypeConverter
    fun jsonToList(value:String)=Gson().fromJson(value,Array<Row>::class.java).toList()
}