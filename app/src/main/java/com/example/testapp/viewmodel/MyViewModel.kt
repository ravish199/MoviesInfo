package com.example.testapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.testapp.api.APICall
import com.example.testapp.data.DataRepository
import com.example.testapp.data.OfflineStorage
import com.example.testapp.data.Storage
import com.example.testapp.db.AppDb
import com.example.testapp.db.MovieDetailsT
import com.example.testapp.db.OfflineDataRepo
import com.example.testapp.db.SearchT
import com.example.testapp.model.Movie
import com.example.testapp.model.MovieDetails
import com.example.testapp.model.Search
import com.example.testapp.utils.AppUtilities
import kotlinx.coroutines.*
import java.lang.Exception

class MyViewModel(application: Application):AndroidViewModel(application) {

    val appContext = application
    var movieLiveData: MutableLiveData<List<Search>> = MutableLiveData()
    var movieDetailsLiveData: MutableLiveData<MovieDetails> = MutableLiveData()
    var errorLiveData:MutableLiveData<Exception> =  MutableLiveData()
    var imdbID = ""
    var title =""
    lateinit var offlineDataRepo: OfflineDataRepo

    init {
       viewModelScope.launch {
            offlineDataRepo = OfflineDataRepo( AppDb.getDatabase(application).searchDao(),
               AppDb.getDatabase(application).movieDetailsDao())
       }
    }

    private fun getStorage() = DataRepository.getDataSource(appContext, offlineDataRepo)

    fun getMovieList(type: String, page: Int, search: String) {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                getStorage().getMovieList(type, page, search).let {
                    movieLiveData.postValue(it)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e)
            }
        }
    }

    fun getMovieDetails(plot: String, title: String, imdbId: String) {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                getStorage().getMovieDetails(plot, title, imdbId).let {
                    movieDetailsLiveData.postValue(it)
                }
            } catch (e:Exception) {
                errorLiveData.postValue(e)
            }
        }
    }
}