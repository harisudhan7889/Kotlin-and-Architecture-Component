package com.hari.kotlintutorial.listing

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hari.kotlintutorial.R
import com.hari.kotlintutorial.models.Movie
import kotlinx.android.synthetic.main.movie_grid_item.view.*

/**
 * Recycler view adapter that holds the list of movies
 * in grid layout.
 *
 * @author Hari Hara Sudhan.N
 */
class MovieListAdapter(listener: ListMovies.View) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var movies: List<Movie> = ArrayList()

    // Kotlin's lazy feature
    // This is like a singleton reference, it gets initialized when it is called first
    // and remembers the result, subsequent calls to it simply return the remembered result.
    // so itemClickListener will initialized when it is called for the first time.
    private val itemClickListener: ListMovies.View by lazy { listener }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent?.context).inflate(R.layout.movie_grid_item, parent, false)
        return ViewHolder(root)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(movies[position])
        }
    }

    /**
     * Assign value to the adapter variable.
     *
     * @param movies - List of movies.
     */
    fun setMovies(movies: List<Movie>?) {
        if (null != movies) {
            this.movies = movies
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        fun bind(movie: Movie) = with(itemView) {
            // Kotlin's Destructuring Declaration
            // if you do not need a variable you can use underscore.
            // In this case i am not going to use first two variables of
            // movie object so added underscore
            val (_, _, posterPath, title) = movie
            movieTitle.text = title
            poster.loadPosterImage(posterPath)
        }

        // Kotlin's Extension function
        private fun ImageView.loadPosterImage(url: String) {
            val posterFullUrl = "http://image.tmdb.org/t/p/w342$url"
            Glide.with(context).load(posterFullUrl).into(this)
        }
    }
}


