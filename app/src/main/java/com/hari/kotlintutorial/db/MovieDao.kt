package com.hari.kotlintutorial.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.hari.kotlintutorial.models.Movie
import io.reactivex.Flowable

/**
 * @author Hari Hara Sudhan.N
 */
@Dao
interface MovieDao {

    /**
     * Get all the movies.
     */
    @Query("Select * FROM movie")
    fun getMovies(): LiveData<List<Movie>>


    /**
     * Get a movie by id.
     */
    @Query("Select * FROM movie WHERE id = :id")
    fun getMovieById(id: String): Flowable<Movie>

    /**
     * Insert a movie in the database. If movie already exists, replace it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>?)

    /**
     * Delete all movies.
     */
    @Query("DELETE FROM movie")
    fun deleteAllMovies()

    @Query("SELECT COUNT(id) FROM movie")
    fun getCount(): Flowable<Int>
}