package com.hari.kotlintutorial.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hari.kotlintutorial.R
import com.hari.kotlintutorial.models.Movie

/**
 * Activity container that contains
 * the view with details of a movie.
 *
 * @author Hari Hara Sudhan.N
 */
class MovieDetailsActivity : AppCompatActivity() {

    /* Kotlin's companion object just like java's static variable/method handling*/
    companion object {
        val MOVIE_DETAILS = "MOVIE_DETAIL"
        fun getIntent(context: Context, movie: Movie): Intent {
            // Automatic type conversion - smart casting
            val intent = Intent(context, MovieDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_DETAILS, movie)
            intent.putExtras(bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        if (null == savedInstanceState) {
            val bundle = intent.extras
            if (null != bundle && bundle.containsKey(MOVIE_DETAILS)) {
                // Safe casting - to avoid class cast exception
                val movie = bundle.get(MOVIE_DETAILS) as? Movie
                supportFragmentManager.beginTransaction()
                        .add(R.id.movie_details_container,
                                MovieDetailsFragment.getInstance(movie),
                                MovieDetailsFragment::class.simpleName)
                        .commit()
            }
        }
    }
}