package com.example.android.appsuperzound.models

import com.google.gson.annotations.SerializedName

class ApiResponseDetails (@SerializedName("loved") var albums: List<Album>)