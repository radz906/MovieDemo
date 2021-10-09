package com.demo.moviedemo.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName ="movieListData" )
data class MovieListData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "title")
    val title: String
)