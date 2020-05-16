package com.example.testapp.db

import androidx.lifecycle.LiveData
import com.example.testapp.model.Search

class OfflineDataRepo(private val searchDao: SearchDao, private val movieDetailsDao: MovieDetailsDao) {

    suspend fun getMoviesList(type: String, page: Int, search: String): List<SearchT> {
       return searchDao.getAllMovies(type,(page - 1) * 10, "${search}%")
    }

    suspend fun insertMovies(movies:List<SearchT>) {
        movies.forEach { searchDao.inserUser(it)}
    }

    suspend fun getMovieDetails(imdbId: String, title:String):MovieDetailsT {
    return movieDetailsDao.getMovieDetails(imdbId, title)
    }

    suspend fun insertMovieDetails(movieDetailsT: MovieDetailsT) {
        movieDetailsDao.inserMovieDetails(movieDetailsT)
    }

}