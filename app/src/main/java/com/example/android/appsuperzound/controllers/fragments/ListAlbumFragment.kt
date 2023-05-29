package com.example.android.appsuperzound.controllers.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.appsuperzound.R
import com.example.android.appsuperzound.adapter.ListAlbumAdapter
import com.example.android.appsuperzound.models.Album
import com.example.android.appsuperzound.models.ApiResponseDetails
import com.example.android.appsuperzound.network.AlbumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListAlbumFragment : Fragment() {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvAlbums)
        loadAlbums(view.context)
    }

    private fun loadAlbums(context: Context) {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://theaudiodb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val albumService: AlbumService = retrofit.create(AlbumService::class.java)
        val request=albumService.getAlbums()
        request.enqueue(object : Callback<ApiResponseDetails> {
            override fun onFailure(call: Call<ApiResponseDetails>, t: Throwable) {
                Log.d("Activity Fail", "Error: $t")
            }

            override fun onResponse(
                call: Call<ApiResponseDetails>,
                response: Response<ApiResponseDetails>
            ) {
                if(response.isSuccessful){
                    val albums: List<Album> = response.body()!!.albums?:ArrayList()
                    recyclerView.layoutManager= LinearLayoutManager(context)
                    recyclerView.adapter= ListAlbumAdapter(albums, context)
                } else{
                    Log.d("Activity fail", "Error: "+response.code())
                }
            }
        })
    }


}