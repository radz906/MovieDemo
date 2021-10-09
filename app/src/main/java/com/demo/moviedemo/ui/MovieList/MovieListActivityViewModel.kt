package com.demo.moviedemo.ui.MovieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

import com.demo.moviedemo.data.model.MovieListData

import com.demo.moviedemo.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieListActivityViewModel (private val movieRepository : MovieListPagedListRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  moviePagedList : LiveData<PagedList<MovieListData>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val  networkState : LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
