package com.assignment.countrylist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.assignment.countrylist.database.AppDatabase
import com.assignment.countrylist.model.DataModels
import com.assignment.countrylist.model.Row
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DatabaseRepository {

    companion object {

        private var appDatabase: AppDatabase? = null
        private var rowTableModel: LiveData<List<Row>>? = null
        private lateinit var title: LiveData<String>

        private fun initializeDB(context: Context): AppDatabase {
            return AppDatabase.getDatabaseClient(context)
        }

        fun insertCountries(context: Context, countries: List<Row>) {
            appDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                appDatabase!!.countryDao().deleteCountryList()
                appDatabase!!.countryDao().insertCountries(countries)
            }
        }


        fun getAllCountry(context: Context): LiveData<List<Row>>? {

            appDatabase = initializeDB(context)

            rowTableModel = appDatabase?.countryDao()?.getCountryList()
            return rowTableModel

        }

        fun insertTitle(context: Context, title: DataModels) {
            appDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                appDatabase!!.countryDao().insertTitle(title)
            }
        }

        fun getTitle(context: Context): LiveData<String> {
            appDatabase = initializeDB(context)
            title = appDatabase!!.countryDao().getTitle()
            return title
        }
    }
}