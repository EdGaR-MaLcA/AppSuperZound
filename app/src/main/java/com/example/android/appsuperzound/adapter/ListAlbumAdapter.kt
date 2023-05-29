package com.example.android.appsuperzound.adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.android.appsuperzound.R
import com.example.android.appsuperzound.database.AlbumDatabase
import com.example.android.appsuperzound.models.Album
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class ListAlbumAdapter(private val albums: List<Album>, private val context: Context): RecyclerView.Adapter<ListAlbumAdapter.ViewHolder>(){
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvAlbum=view.findViewById<TextView>(R.id.tvNameAlbum)
        val tvArtist=view.findViewById<TextView>(R.id.tvArtist)
        val ivAlbumImage=view.findViewById<ImageView>(R.id.ivAlbum)
        val tvYear=view.findViewById<TextView>(R.id.tvYear)
        val faFavorite=view.findViewById<FloatingActionButton>(R.id.faFavorite)
        val rbScore=view.findViewById<RatingBar>(R.id.rbScore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context)
            .inflate(R.layout.prototype_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album=albums[position]
        holder.tvAlbum.text=album.name_album
        holder.tvArtist.text=album.artist
        val picBiulder = Picasso.Builder(context)
        picBiulder.downloader(OkHttp3Downloader(context))
        picBiulder.build().load(album.image_album)
            //lineas opcionales para gestionar errores
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivAlbumImage)
        holder.tvYear.text=album.year.toString()
        holder.rbScore.setIsIndicator(true)
        holder.rbScore.numStars = 5
        holder.rbScore.stepSize = 1.0f
        holder.rbScore.max = 10
        holder.rbScore.rating=album.score
        holder.faFavorite.setOnClickListener{
            saveAlbum(album)
        }
    }
    private fun saveAlbum(album: Album) {
        AlbumDatabase.getInstance(this.context).getAlbumDAO().insertAlbum(album)
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}