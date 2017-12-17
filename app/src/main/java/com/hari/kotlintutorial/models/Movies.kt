package com.hari.kotlintutorial.models

import com.google.gson.annotations.SerializedName

/**
 * Data class contains list of movie.
 *
 * @author Hari Hara Sudhan.N
 */
class Movies {
    /* Late initialization.
     Since the value of variable `movies` will initialized
     as the response of the service call. In this case,
     you cannot supply a non-null initializer, but you
     can avoid null checks when referencing
     lateinit property. Just remove lateinit property and check
     for your testing purpose.*/
    @SerializedName("results")
    lateinit var movies: List<Movie>
}