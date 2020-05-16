package com.example.testapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testapp.model.MovieDetails
import com.example.testapp.model.Search

@Database(entities = arrayOf(SearchT::class, MovieDetailsT::class), version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun searchDao():SearchDao
    abstract fun movieDetailsDao():MovieDetailsDao

    companion object {
        @Volatile
        private var INSTANCE:AppDb? =null

      fun getDatabase(context:Context): AppDb {
            var tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDb::class.java,
                    "MyDatabase").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
