package com.assignment.countrylist.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assignment.countrylist.model.DataModels
import com.assignment.countrylist.model.Row
@Database(entities = [Row::class, DataModels::class], version = 1,exportSchema =  false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabaseClient(context: Context): AppDatabase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "country_database")
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!
            }
        }
    }
}