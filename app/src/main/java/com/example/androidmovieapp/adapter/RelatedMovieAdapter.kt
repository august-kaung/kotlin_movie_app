
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
import com.example.androidmovieapp.model.CastX
import com.example.androidmovieapp.model.MovieResult
import com.example.androidmovieapp.model.ResultX


class RelatedMovieAdapter(private var relatedMovies: List<CastX>  ):
    RecyclerView.Adapter<RelatedMovieAdapter.RelatedMovieViewHolder>() {

    inner class RelatedMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.imgMoviePoster)
        val txtTitle: TextView = itemView.findViewById(R.id.txtMovieTitle)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return RelatedMovieViewHolder(view)
    }

    override fun getItemCount(): Int = relatedMovies.size

    override fun onBindViewHolder(holder: RelatedMovieViewHolder, position: Int) {
        val singleMovieRelated = relatedMovies[position]
        holder.txtTitle.text = singleMovieRelated.title
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${singleMovieRelated.poster_path}")
            .apply(RequestOptions().transform(RoundedCorners(20))) // 20dp corner radius
            .into(holder.imgPoster)
        holder.txtTitle.isSelected = true
    }

    fun updateData(newRelatedMovies: List<CastX>) {
        relatedMovies = newRelatedMovies
        notifyDataSetChanged()
    }


}