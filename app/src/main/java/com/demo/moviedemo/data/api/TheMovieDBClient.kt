package com.demo.moviedemo.data.api

import android.content.Context
import com.demo.moviedemo.data.local.MovieDao
import com.demo.moviedemo.data.local.MovieDataBase
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val API_KEY = "27007e77080bddc69eb9f6c596400e2e"
const val LANGUAGE="en-US"

const val BASE_URL = "https://api.themoviedb.org/3/"
//const val BASE_URL = "https://api.themoviedb.org/3/movie/now_playing?"

const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"


const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20





object TheMovieDBClient {
    @Singleton
    @Provides
    fun getAppDatabase(context: Context): MovieDataBase {
        return MovieDataBase.getAppDbInstance(context)
    }

    @Singleton
    @Provides
    fun appDao(movieDataBase: MovieDataBase): MovieDao {
        return movieDataBase.getMovieDao()
    }
    fun getClient(): TheMovieDBInterface {

        val requestInterceptor = Interceptor { chain ->
            // Interceptor take only one argument which is a lambda function so parenthesis can be omitted

            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)   //explicitly return a value from whit @ annotation. lambda always returns the value of the last expression implicitly
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieDBInterface::class.java)

    }
}