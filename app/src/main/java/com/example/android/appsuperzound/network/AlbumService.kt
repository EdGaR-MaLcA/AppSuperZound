package com.example.android.appsuperzound.network

import com.example.android.appsuperzound.models.ApiResponseDetails
import retrofit2.Call
import retrofit2.http.GET

interface AlbumService {
    @GET("api/v1/json/523532/mostloved.php?format=album")
    fun getAlbums(): Call<ApiResponseDetails>
}