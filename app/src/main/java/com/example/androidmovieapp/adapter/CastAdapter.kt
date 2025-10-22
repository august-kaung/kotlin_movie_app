
package com.example.androidmovieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.androidmovieapp.R
import com.example.androidmovieapp.model.Cast
import com.example.androidmovieapp.model.MovieResult
import com.example.androidmovieapp.model.ResultX


class CastAdapter(private var casts: List<Cast>) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.imgMoviePoster)
        val txtTitle: TextView = itemView.findViewById(R.id.txtMovieTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return CastViewHolder(view)
    }

    override fun getItemCount(): Int = casts.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val actor = casts[position]
        holder.txtTitle.text = actor.name
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${actor.profile_path}")
            .apply(RequestOptions().transform(RoundedCorners(20))) // 20dp corner radius
            .into(holder.imgPoster)
        holder.txtTitle.isSelected=true
    }

    fun updateData(newCasts: List<Cast>) {
        casts = newCasts
        notifyDataSetChanged()
    }


}