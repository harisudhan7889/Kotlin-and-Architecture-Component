package com.hari.kotlintutorial.listing

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.hari.kotlintutorial.db.MovieRepository
import com.hari.kotlintutorial.models.Movie

/**
 * @author Hari Hara Sudhan.N
 */
class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var isFirstTime = true
    private var liveData: LiveData<List<Movie>>? = null

    fun getMoviesFromDatabase(): LiveData<List<Movie>> {
        if (null == liveData) {
            liveData = movieRepository.getMovies()
        }
        return liveData as LiveData<List<Movie>>
    }

    fun getMoviesFromRemoteAndSave() {
        if (isFirstTime) {
            movieRepository.getMoviesFromRemoteAndSave()
            isFirstTime = false
        }
    }
}