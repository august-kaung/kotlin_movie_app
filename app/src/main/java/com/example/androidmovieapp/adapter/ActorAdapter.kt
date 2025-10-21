package com.example.androidmovieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidmovieapp.R
import com.example.androidmovieapp.model.MovieResult
import com.example.androidmovieapp.model.ResultX


class ActorAdapter(private var actors: List<ResultX>) :
    RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.imgMoviePoster)
        val txtTitle: TextView = itemView.findViewById(R.id.txtMovieTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ActorViewHolder(view)
    }

    override fun getItemCount(): Int = actors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actors[position]
        holder.txtTitle.text = actor.name
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${actor.profile_path}")
            .into(holder.imgPoster)
    }

    fun updateData(newActors: List<ResultX>) {
        actors = newActors
        notifyDataSetChanged()
    }


}