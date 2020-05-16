package com.example.testapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.testapp.model.MovieDetails
import com.example.testapp.model.Search

@Dao
interface MovieDetailsDao {
    @Query("Select * from MovieDetails where imdbID = :imdbId and title =:title")
    fun getMovieDetails(imdbId: String, title:String): MovieDetailsT

    @Insert
    fun inserMovieDetails(movieDetails: MovieDetailsT)
}
