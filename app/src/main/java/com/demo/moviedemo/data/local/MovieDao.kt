package com.demo.moviedemo.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demo.moviedemo.data.model.MovieListData

import io.reactivex.Observable

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieListData")
    fun getAllRecords(): LiveData<List<MovieListData>>

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun insertRecords(movieListData: MovieListData)

    @Query("DELETE FROM movieListData")
    fun deleteAllRecords()

}