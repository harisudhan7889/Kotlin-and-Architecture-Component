package com.hari.kotlintutorial.listing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hari.kotlintutorial.R
import com.hari.kotlintutorial.details.MovieDetailsActivity
import com.hari.kotlintutorial.models.Movie

/**
 * Activity container that holds the movie list view.
 *
 * @author Hari Hara Sudhan.N
 */
class MovieListActivity : AppCompatActivity(), MovieListCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        loadMovies(savedInstanceState)
    }

    /**
     * Starts the activity that shows details about the selected movie.
     * @param movie - Selected movie.
     * @see MovieListAdapter
     * @see MoviesListFragment
     */
    override fun showAboutMovie(movie: Movie) {
        startActivity(MovieDetailsActivity.getIntent(this, movie))
    }

    private fun loadMovies(savedInstanceState: Bundle?) {
        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.movie_list_container, MoviesListFragment(), MoviesListFragment::class.simpleName)
                    .commit()
        }
    }
}
