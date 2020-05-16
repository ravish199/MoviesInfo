package com.example.testapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.testapp.model.MovieDetails
import com.example.testapp.model.Search

@Dao
interface SearchDao {
    @Query("Select * from Search where type = :type and title like :title limit 10 offset :offset")
    fun getAllMovies(type:String, offset:Int, title:String): List<SearchT>

    @Insert
    fun inserUser(searchList: SearchT)
}
