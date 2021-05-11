package com.assignment.countrylist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.countrylist.model.DataModels
import com.assignment.countrylist.model.Row

@Dao
interface CountryDao {
    @Query(  "SELECT * FROM `Row` ")
    fun getCountryList() : LiveData<List<Row>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountries(countries: List<Row>)
    @Query("DELETE FROM `Row`")
    fun deleteCountryList()
    @Insert
    fun insertTitle(title: DataModels)
    @Query("SELECT title FROM DataModels ")
    fun getTitle() : LiveData<String>
}