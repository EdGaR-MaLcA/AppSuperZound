package com.example.android.appsuperzound.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.appsuperzound.R
import com.example.android.appsuperzound.database.AlbumDatabase
import com.example.android.appsuperzound.models.Album
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class SavedAlbumAdapter (private val savedAlbums: List<Album>, private val context: Context): RecyclerView.Adapter<SavedAlbumAdapter.ViewHolder>(){
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvName=view.findViewById<TextView>(R.id.tvName)
        val tvGenre=view.findViewById<TextView>(R.id.tvGenre)
        val ivCD=view.findViewById<ImageView>(R.id.ivCD)
        val fabDelete=view.findViewById<FloatingActionButton>(R.id.fabDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_saved_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album=savedAlbums[position]
        holder.tvName.text=album.name_album
        holder.tvGenre.text=album.genre
        val picBiulder = Picasso.Builder(context)
        picBiulder.downloader(OkHttp3Downloader(context))
        picBiulder.build().load(album.cd_photo)
            //lineas opcionales para gestionar errores
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivCD)
        holder.fabDelete.setOnClickListener{
            deleteAlbum(album)
        }
    }

    private fun deleteAlbum(album: Album) {
        AlbumDatabase.getInstance(this.context).getAlbumDAO().deleteAlbum(album)
        Toast.makeText(this.context, "Album deleted, go to home or album section to refresh.", Toast.LENGTH_SHORT).show();
    }


    override fun getItemCount(): Int {
        return savedAlbums.size
    }

}