package com.example.android.appsuperzound.controllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.appsuperzound.R
import com.example.android.appsuperzound.adapter.SavedAlbumAdapter
import com.example.android.appsuperzound.database.AlbumDatabase
import com.example.android.appsuperzound.models.Album

class FavouriteAlbumFragment : Fragment() {

    var savedAlbums: List<Album> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedAlbums= AlbumDatabase.getInstance(view.context).getAlbumDAO().getAllAlbums()
        recyclerView=view.findViewById(R.id.rvSavedAlbums)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter= SavedAlbumAdapter(savedAlbums, view.context)
    }
}