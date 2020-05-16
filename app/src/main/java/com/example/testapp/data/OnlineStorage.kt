package com.example.testapp.data

import androidx.lifecycle.viewModelScope
import com.example.testapp.api.APICall
import com.example.testapp.db.MovieDetailsT
import com.example.testapp.db.OfflineDataRepo
import com.example.testapp.db.SearchT
import com.example.testapp.model.Movie
import com.example.testapp.model.MovieDetails
import com.example.testapp.model.Search
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class OnlineStorage(private var offlineDataRepo: OfflineDataRepo): Storage{
    private val apiKey = "5d81e1ce"
    override suspend fun getMovieList(type: String, page: Int, search: String):List<Search>? {
        var searchList:List<Search>? =null
                APICall.getApi.getMovieList(type, apiKey, page, search)?.execute()?.let {
                 searchList = it.body()?.search
                    insertMovieList(searchList)
        }
        return searchList
    }

    override suspend fun getMovieDetails(plot: String, title: String, imdbId: String): MovieDetails? {
        var moviesDetails:MovieDetails? = null
                APICall.getApi.getMovieDetails(plot, apiKey, title, imdbId)?.execute()?.let {
                    moviesDetails = it.body()
                    insertMovieDetails(moviesDetails)
        }
        return moviesDetails
    }

    private suspend fun insertMovieList(searchList:List<Search>?) {
        val mlist = ArrayList<SearchT>()
        searchList?.forEach { mlist.add(SearchT(it.imdbID!!, it.title, it.year, it.type, it.poster)) }
        try {
            offlineDataRepo.insertMovies(mlist)
        } catch (e:Exception){
            e.printStackTrace()
        }

    }

    suspend fun insertMovieDetails(m: MovieDetails?) {
        if(m != null) {
            var movieDetailsT = MovieDetailsT(
                m.imdbID!!,
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
                m.poster,
                m.ratingsValue,
                m.metascore,
                m.imdbRating,
                m.imdbVotes,
                m.type,
                m.dVD,
                m.boxOffice,
                m.production,
                m.website,
                m.response
            )
            try {
                offlineDataRepo.insertMovieDetails(movieDetailsT)
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }
}