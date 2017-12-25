package com.hari.kotlintutorial.listing

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hari.kotlintutorial.R
import com.hari.kotlintutorial.api.MovieApi
import com.hari.kotlintutorial.db.MovieDatabase
import com.hari.kotlintutorial.db.MovieRepository
import com.hari.kotlintutorial.models.Movie
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * View that renders the list of movies.
 *
 * @author Hari Hara Sudhan.N
 */
class MoviesListFragment : Fragment(), ListMovies.View {

    private lateinit var listMoviesPresenter: ListMovies.Preseneter
    private lateinit var callback: MovieListCallback
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieDatabase = MovieDatabase.getInstance(context)

        // Repositary for handling Remote services and explicit database handling
        val movieRepository = MovieRepository(MovieApi.create(), movieDatabase.MovieDao())
        viewModelFactory = ViewModelFactory(movieRepository)
        movieListViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
        //listMoviesPresenter = ListMoviesPresenterImpl(this)
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

    /*
     This method is not used. This is to show how we would handled if we did not
     use repository module in ViewModel and if we used presenter.
     */
    override fun insertInDatabase(movies: List<Movie>?) {
        movies?.let {
            //movieListViewModel.updateMovies(movies)
        }
    }

    private fun showMovies(movies: Flowable<List<Movie>>) {
        movies.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ showMovies(it) }, { e -> Log.e("MoviesListFragment", e.toString()) })
    }

    private fun showMovies(movies: List<Movie>?) {
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
        movieListViewModel.getMoviesFromDatabase().observe(this, Observer { movies -> showMovies(movies) })
        movieListViewModel.getMoviesFromRemoteAndSave()
    }
}