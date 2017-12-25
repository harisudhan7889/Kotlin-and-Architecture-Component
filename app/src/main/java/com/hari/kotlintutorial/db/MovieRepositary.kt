package com.hari.kotlintutorial.db

import android.arch.lifecycle.LiveData
import com.hari.kotlintutorial.api.MovieApi
import com.hari.kotlintutorial.models.Movie
import com.hari.kotlintutorial.models.Movies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

/**
 * @author Hari Hara Sudhan.N
 */

class MovieRepository(private val movieApi: MovieApi, private val movieDao: MovieDao) {

    fun getMoviesFromRemoteAndSave() {
        movieDao.getCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it == 0) {
                        getMoviesFromRemote()
                    }
                }, { e -> onFailure(e) })
    }

    private fun getMoviesFromRemote() {
        // Lambda expression which reduces the no of lines,
        // so code understanding will be easy.
        movieApi.getMovies(createQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movies -> onSuccess(movies) },
                        { e -> onFailure(e) })
    }

    /**
     * Gets the list of movies and pass it to the view for UI rendering.
     * @param movies - object that contains list of movies.
     */
    private fun onSuccess(movies: Movies) {
        // Null safety returns null if movies is null otherwise returns value
        val listOfMovies = movies.movies
        // List.filter is one of the feature in list exist in kotlin.
        // Filtering the list with a condition.
        listOfMovies.filter { "" != it.id }

        // Cannot update the DB in main thread, so created a background thread
        val executor = Executors.newFixedThreadPool(1)
        val worker = Runnable { movieDao.insertMovies(movies.movies) }
        executor.execute(worker)
    }

    /**
     * Method called when service fails.
     */
    private fun onFailure(e: Throwable?) {
        print(e?.message)
    }

    fun getMovies(): LiveData<List<Movie>> {
        return movieDao.getMovies()
    }

    /**
     * Creates the input for the service call which gets
     * the movies from movie data base.
     */
    private fun createQueryMap(): Map<String, String> {
        return hashMapOf(
                "language" to "en",
                "sort_by" to "popularity.desc")
    }
}