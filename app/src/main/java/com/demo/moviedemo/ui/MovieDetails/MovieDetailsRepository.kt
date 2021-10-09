package com.demo.moviedemo.ui.MovieDetails

import androidx.lifecycle.LiveData
import com.demo.moviedemo.data.api.TheMovieDBInterface
import com.demo.moviedemo.data.model.MovieDetailsData
import com.demo.moviedemo.data.repository.MovieDetailsNetworkDataSource
import com.demo.moviedemo.data.repository.NetworkState

import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService : TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetailsData> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }



}