package com.demo.moviedemo.ui.MovieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demo.moviedemo.data.model.MovieDetailsData
import com.demo.moviedemo.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable


class MovieDetailsViewModel (private val movieRepository : MovieDetailsRepository, movieId: Int)  : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  movieDetails : LiveData<MovieDetailsData> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable,movieId)
    }

    val networkState : LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }



}