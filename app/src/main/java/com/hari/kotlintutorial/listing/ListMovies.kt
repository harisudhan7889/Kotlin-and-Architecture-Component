package com.hari.kotlintutorial.listing

import com.hari.kotlintutorial.models.Movie

/**
 * @author Hari Hara Sudhan.N
 */
interface ListMovies {

    /*
     Callback listener to view.
     */
    interface View {
        fun insertInDatabase(movies: List<Movie>?)
        fun onItemClick(movie: Movie)
    }

    /*
    Listener to presenter.
     */
    interface Preseneter {
        fun getMovies()
    }
}