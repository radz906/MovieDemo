package com.demo.moviedemo.data.api

import com.demo.moviedemo.data.model.MovieDetailsData
import com.demo.moviedemo.data.model.MovieResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {


    @GET("movie/now_playing")
    fun getNowPlayingMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetailsData>
}