package com.example.android.appsuperzound.database

import androidx.room.*
import com.example.android.appsuperzound.models.Album

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    fun getAllAlbums(): List<Album>

    @Insert
    fun insertAlbum(vararg album: Album)

    @Update
    fun updateAlbum(vararg album: Album)

    @Delete
    fun deleteAlbum(vararg album: Album)
}