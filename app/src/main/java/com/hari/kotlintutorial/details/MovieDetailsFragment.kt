package com.hari.kotlintutorial.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hari.kotlintutorial.R
import com.hari.kotlintutorial.models.Movie
import kotlinx.android.synthetic.main.fragment_movie_detail.*

/**
 * View that renders the movie details.
 *
 * @author Hari Hara Sudhan.N
 */
class MovieDetailsFragment : Fragment() {

    private var movie: Movie? = null

    /* Kotlin's companion object just like java's static variable/method handling*/
    companion object {
        fun getInstance(movie: Movie?): MovieDetailsFragment {
            val bundle = Bundle()
            bundle.putParcelable(MovieDetailsActivity.MOVIE_DETAILS, movie)
            val movieDetailsFragment = MovieDetailsFragment()
            movieDetailsFragment.arguments = bundle
            return movieDetailsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null != arguments) {
            // Safe casting - to avoid Class cast exception
            movie = arguments.get(MovieDetailsActivity.MOVIE_DETAILS) as? Movie
        }
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_movie_detail, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBar()
        showMovieDetails()
    }

    /**
     * Sets the collapsing toolbar to the activity's actionbar.
     */
    private fun setToolBar() {
        // Kotlin's view Extension
        collapsing_toolbar?.title = movie?.title
        collapsing_toolbar?.isTitleEnabled = true
        collapsing_toolbar?.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar)
        collapsing_toolbar?.setExpandedTitleTextAppearance(R.style.ExpandedToolbar)
        if (null != toolbar) {
            // Here i know activity's instance will be of AppCompatActivity
            // so i did not do safe casting over here
            val activity = (activity as AppCompatActivity)
            activity.setSupportActionBar(toolbar)
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * Set values to view.
     */
    private fun showMovieDetails() {
        // Safe calls to avoid null pointer exception
        // Kotlin's Elvis Operator - when we have nullable reference `movie`
        // we can say if `movie` is not null use it, otherwise use some non-null value.
        movie_poster.loadImage(movie?.backdropPath ?: "")
        movie_name.text = movie?.title
        movie_year.text = String.format(getString(R.string.release_date), movie?.releaseDate)
        movie_rating.text = String.format(getString(R.string.rating), movie?.voteAverage)
        // Just to show example how `!!` feature works
        // If user wish to throw exception can use `!!`
        movie_description.text = movie!!.overview
    }

    /*
       Kotlin's Extension function.
    */
    private fun ImageView.loadImage(url: String?) {
        val posterFullUrl = "http://image.tmdb.org/t/p/w342$url"
        Glide.with(context).load(posterFullUrl).into(this)
    }
}