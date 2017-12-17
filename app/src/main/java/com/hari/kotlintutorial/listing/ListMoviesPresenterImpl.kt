package com.hari.kotlintutorial.listing

import com.hari.kotlintutorial.api.MovieApi
import com.hari.kotlintutorial.models.Movies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Hari Hara Sudhan.N
 */

class ListMoviesPresenterImpl(private val context: MoviesListFragment) : ListMovies.Preseneter {

    /**
     * Service call to get movies from TMDB - The Movie Data Base.
     */
    override fun getMovies() {
        // Lambda expression which reduces the no of lines,
        // so code understanding will be easy.
        val movieApi = MovieApi.create()
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
        (context as ListMovies.View).showMovies(listOfMovies)
    }

    /**
     * Method called when service fails.
     */
    private fun onFailure(e: Throwable?) {
        print(e?.message)
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
