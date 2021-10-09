package com.demo.moviedemo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.moviedemo.data.model.MovieListData

@Database(entities = [MovieListData::class], version = 1,exportSchema = false)
abstract  class MovieDataBase :RoomDatabase(){
    abstract fun getMovieDao(): MovieDao
    companion object {
        private var DB_INSTANCE: MovieDataBase? = null

        fun getAppDbInstance(context: Context): MovieDataBase {
            if(DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder<MovieDataBase>(
                    context.applicationContext, MovieDataBase::class.java, "MYDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }

    }
}