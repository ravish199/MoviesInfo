package com.example.testapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APICall {

    private val baseUrl = "http://www.omdbapi.com/"
    val getApi:API by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
         retrofit.create(API::class.java)
    }

}