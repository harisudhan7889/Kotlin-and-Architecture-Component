package com.hari.kotlintutorial.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Data class contains details of a movie.
 *
 * @author Hari Hara Sudhan.N
 */
data class Movie(val id: String,
                 val overview: String,
                 @SerializedName("poster_path") val posterPath: String,
                 val title: String,
                 @SerializedName("vote_average") val voteAverage: String,
                 @SerializedName("vote_count") private val voteCount: String,
                 @SerializedName("backdrop_path") val backdropPath: String,
                 @SerializedName("release_date") val releaseDate: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(overview)
        parcel.writeString(posterPath)
        parcel.writeString(title)
        parcel.writeString(voteAverage)
        parcel.writeString(voteCount)
        parcel.writeString(backdropPath)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

    private fun getPosterUrl(): String {
        return "http://image.tmdb.org/t/p/w342$posterPath"
    }
}