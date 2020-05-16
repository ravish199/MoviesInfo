package com.example.testapp.data

import com.example.testapp.model.MovieDetails
import com.example.testapp.model.Search

interface Storage {
    suspend fun getMovieList(type: String, page: Int, search: String):List<Search>?
    suspend fun getMovieDetails(plot: String, title: String, mdbId: String): MovieDetails?
}