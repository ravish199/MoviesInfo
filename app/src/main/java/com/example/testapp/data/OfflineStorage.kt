package com.example.testapp.data

import com.example.testapp.api.APICall
import com.example.testapp.db.OfflineDataRepo
import com.example.testapp.db.SearchT
import com.example.testapp.model.MovieDetails
import com.example.testapp.model.Search
import com.example.testapp.viewmodel.MyViewModel

class OfflineStorage(private var offlineDataRepo: OfflineDataRepo): Storage {

    override suspend fun getMovieList(type: String, page: Int, search: String):List<Search>? {
        var searchList = ArrayList<Search>()
      offlineDataRepo.getMoviesList(type, page, search).forEach { searchList.add(Search(it.title, it.year, it.imdbID, it.type, it.poster)) }
        return searchList
    }

    override suspend fun getMovieDetails(plot: String, title: String, imdbId: String): MovieDetails? {
        var moviesDetails: MovieDetails? = null
       val m = offlineDataRepo.getMovieDetails(imdbId, title)
        moviesDetails = MovieDetails(
            m.title,
            m.year,
            m.rated,
            m.released,
            m.runtime,
            m.genre,
            m.director,
            m.writer,
            m.actors,
            m.plot,
            m.language,
            m.country,
            m.awards,
            m.poster!!,
            m.ratings!!,
            m.metascore,
            m.imdbRating,
            m.imdbVotes,
            m.imdbID,
            m.type,
            m.dVD,
            m.boxOffice,
            m.production,
            m.website,
            m.response)
        return moviesDetails
    }
}