package com.example.testapp.api

import androidx.lifecycle.LiveData
import com.example.testapp.model.Movie
import com.example.testapp.model.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET(".")
     fun getMovieList(@Query("type") type:String, @Query("apikey") apikey:String,
                     @Query("page") page:Int , @Query("s") search:String): Call<Movie>?

    @GET(".")
     fun getMovieDetails(@Query("plot") plot:String, @Query("apikey") apikey:String,
                        @Query("t") title:String,  @Query("imdbId") imdbId:String ): Call<MovieDetails>?
}