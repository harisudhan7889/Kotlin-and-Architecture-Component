package com.hari.kotlintutorial.listing

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.hari.kotlintutorial.db.MovieRepository

/**
 * @author Hari Hara Sudhan.N
 */
class ViewModelFactory(private val movieRespositary:MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(movieRespositary) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}