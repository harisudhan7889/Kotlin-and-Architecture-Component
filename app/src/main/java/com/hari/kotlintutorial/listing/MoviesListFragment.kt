package com.hari.kotlintutorial.listing

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hari.kotlintutorial.R
import com.hari.kotlintutorial.models.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * View that renders the list of movies.
 *
 * @author Hari Hara Sudhan.N
 */
class MoviesListFragment : Fragment(), ListMovies.View {

    lateinit private var listMoviesPresenter: ListMovies.Preseneter
    lateinit private var callback: MovieListCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listMoviesPresenter = ListMoviesPresenterImpl(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        // This check is nothing but `instanceOf` check in java
        if (context is MovieListCallback) {
            // Automatic typecasting
            callback = context
        }
    }

    override fun showMovies(movies: List<Movie>?) {
        // Type Casting
        (movies_listing.adapter as MovieListAdapter).setMovies(movies)
    }

    /**
     * A callback from the recycler view adapter,
     * invoked when a item is clicked.
     *
     * @param movie - Selected movie object.
     * @see MovieListAdapter for the item click listener.
     */
    override fun onItemClick(movie: Movie) {
        callback.showAboutMovie(movie)
    }

    @SuppressLint("NewApi")
    private fun init() {
        val gridLayoutManager = GridLayoutManager(context, 2)
        movies_listing.layoutManager = gridLayoutManager
        // Kotlin's view extension - here i did not use findViewById or
        // any view injection. Kotlin has one of the cool view extension feature.
        // Can use the same name as given in the xml file. When the view gets
        // created, values get assigned to this variable.
        movies_listing.setHasFixedSize(true)
        movies_listing.adapter = MovieListAdapter(this)
        listMoviesPresenter.getMovies()
    }
}