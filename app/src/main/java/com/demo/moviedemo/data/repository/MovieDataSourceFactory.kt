package com.demo.moviedemo.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.demo.moviedemo.data.api.TheMovieDBInterface
import com.demo.moviedemo.data.model.MovieListData

import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory (private val apiService : TheMovieDBInterface, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, MovieListData>() {

    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MovieListData> {
        val movieDataSource = MovieDataSource(apiService,compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}