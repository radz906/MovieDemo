package com.demo.moviedemo.ui.MovieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.demo.moviedemo.data.api.POST_PER_PAGE

import com.demo.moviedemo.data.api.TheMovieDBInterface
import com.demo.moviedemo.data.model.MovieListData
import com.demo.moviedemo.data.repository.MovieDataSource
import com.demo.moviedemo.data.repository.MovieDataSourceFactory
import com.demo.moviedemo.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable


class MovieListPagedListRepository (private val apiService : TheMovieDBInterface) {

    lateinit var moviePagedList: LiveData<PagedList<MovieListData>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<MovieListData>> {
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }
}