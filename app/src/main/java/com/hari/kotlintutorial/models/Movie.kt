package com.hari.kotlintutorial.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Data class contains details of a movie.
 *
 * @author Hari Hara Sudhan.N
 */
@Entity(tableName = "movie")
data class Movie(@PrimaryKey
                 @ColumnInfo(name = "id") val id: String,
                 @ColumnInfo(name = "description") val overview: String,
                 @ColumnInfo(name = "poster")
                 @SerializedName("poster_path") val posterPath: String,
                 @ColumnInfo(name = "title") val title: String,
                 @ColumnInfo(name = "vote_average")
                 @SerializedName("vote_average") val voteAverage: String,
                 @ColumnInfo(name = "vote_count")
                 @SerializedName("vote_count") val voteCount: String,
                 @ColumnInfo(name = "backdrop")
                 @SerializedName("backdrop_path") val backdropPath: String,
                 @ColumnInfo(name = "release_date")
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