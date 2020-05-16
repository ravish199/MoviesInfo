package com.example.testapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Search")
data class SearchT(
    @PrimaryKey() val imdbID:String,
 val title:String?,
 val year:String?,
    val type:String?,
    val poster:String?
)
