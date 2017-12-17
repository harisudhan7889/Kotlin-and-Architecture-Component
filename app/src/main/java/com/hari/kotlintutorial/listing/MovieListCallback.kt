package com.hari.kotlintutorial.listing

import com.hari.kotlintutorial.models.Movie

/**
 * @author Hari Hara Sudhan.N
 */
interface MovieListCallback {
  fun showAboutMovie(movie: Movie)
}