package com.example.android.appsuperzound.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "albums")
class Album (
    @PrimaryKey
    @SerializedName("idAlbum")
    var album_id: Int,
    @SerializedName("strAlbum")
    var name_album: String,
    @SerializedName("strArtist")
    var artist: String,
    @SerializedName("strAlbumThumb")
    var image_album: String,
    @SerializedName("intYearReleased")
    var year: Int,
    @SerializedName("intScore")
    var score: Float,
    @SerializedName("strGenre")
    var genre: String,
    @SerializedName("strAlbum3DCase")
    var cd_photo: String
): Serializable