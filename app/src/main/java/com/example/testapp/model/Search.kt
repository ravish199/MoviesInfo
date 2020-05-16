package com.example.testapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Search {
    @SerializedName("Title")
    @Expose
    var title: String? = null

    constructor(title: String?, year: String?, imdbID: String?, type: String?, poster: String?) {
        this.title = title
        this.year = year
        this.imdbID = imdbID
        this.type = type
        this.poster = poster
    }

    @SerializedName("Year")
    @Expose
    var year: String? = null

    @SerializedName("imdbID")
    @Expose
    var imdbID: String? = null

    @SerializedName("Type")
    @Expose
    var type: String? = null

    @SerializedName("Poster")
    @Expose
    var poster: String? = null
}
