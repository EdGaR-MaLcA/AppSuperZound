package com.example.android.appsuperzound.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.appsuperzound.models.Album

@Database(entities = [Album::class], version = 1)
abstract class AlbumDatabase : RoomDatabase(){
    abstract fun getAlbumDAO(): AlbumDao

    companion object{ //patron singleton
        private var INSTANCE : AlbumDatabase? = null

        fun getInstance(context: Context): AlbumDatabase{
            if (INSTANCE == null){
                //la bd no existe
                INSTANCE = Room
                    .databaseBuilder(context, AlbumDatabase::class.java, "albumv1.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AlbumDatabase
        }
    }
}