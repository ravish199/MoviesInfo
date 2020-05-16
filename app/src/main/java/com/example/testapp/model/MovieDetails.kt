package com.example.testapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDetails {
    @SerializedName("Title")
    @Expose
    var title: String? = null

    @SerializedName("Year")
    @Expose
    var year: String? = null

    constructor(
        title: String?,
        year: String?,
        rated: String?,
        released: String?,
        runtime: String?,
        genre: String?,
        director: String?,
        writer: String?,
        actors: String?,
        plot: String?,
        language: String?,
        country: String?,
        awards: String?,
        poster: String?,
        ratingsValue: String,
        metascore: String?,
        imdbRating: String?,
        imdbVotes: String?,
        imdbID: String?,
        type: String?,
        dVD: String?,
        boxOffice: String?,
        production: String?,
        website: String?,
        response: String?
    ) {
        this.title = title
        this.year = year
        this.rated = rated
        this.released = released
        this.runtime = runtime
        this.genre = genre
        this.director = director
        this.writer = writer
        this.actors = actors
        this.plot = plot
        this.language = language
        this.country = country
        this.awards = awards
        this.poster = poster
        this.ratings = ratings
        this.metascore = metascore
        this.imdbRating = imdbRating
        this.imdbVotes = imdbVotes
        this.imdbID = imdbID
        this.type = type
        this.dVD = dVD
        this.boxOffice = boxOffice
        this.production = production
        this.website = website
        this.response = response
        this.ratingsValue = ratingsValue
    }

    @SerializedName("Rated")
    @Expose
    var rated: String? = null

    @SerializedName("Released")
    @Expose
    var released: String? = null

    @SerializedName("Runtime")
    @Expose
    var runtime: String? = null

    @SerializedName("Genre")
    @Expose
    var genre: String? = null

    @SerializedName("Director")
    @Expose
    var director: String? = null

    @SerializedName("Writer")
    @Expose
    var writer: String? = null

    @SerializedName("Actors")
    @Expose
    var actors: String? = null

    @SerializedName("Plot")
    @Expose
    var plot: String? = null

    @SerializedName("Language")
    @Expose
    var language: String? = null

    @SerializedName("Country")
    @Expose
    var country: String? = null

    @SerializedName("Awards")
    @Expose
    var awards: String? = null

    @SerializedName("Poster")
    @Expose
    var poster: String? = null

    @SerializedName("Ratings")
    @Expose
    var ratings: List<Rating>? = null

    @SerializedName("Metascore")
    @Expose
    var metascore: String? = null

    @SerializedName("imdbRating")
    @Expose
    var imdbRating: String? = null

    @SerializedName("imdbVotes")
    @Expose
    var imdbVotes: String? = null

    @SerializedName("imdbID")
    @Expose
    var imdbID: String? = null

    @SerializedName("Type")
    @Expose
    var type: String? = null

    @SerializedName("DVD")
    @Expose
    var dVD: String? = null

    @SerializedName("BoxOffice")
    @Expose
    var boxOffice: String? = null

    @SerializedName("Production")
    @Expose
    var production: String? = null

    @SerializedName("Website")
    @Expose
    var website: String? = null

    @SerializedName("Response")
    @Expose
    var response: String? = null

    var ratingsValue = ""

}