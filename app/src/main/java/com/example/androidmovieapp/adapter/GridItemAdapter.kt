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
import com.example.androidmovieapp.model.ResultXX
import com.example.androidmovieapp.model.SearchResponse

class GridItemAdapter(private val searchResult: List<ResultXX>, private val onItemClick: (ResultXX) -> Unit  ) :
    RecyclerView.Adapter<GridItemAdapter.GridViewHolder>() {

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.imgMoviePoster)
        val txtTitle: TextView = itemView.findViewById(R.id.txtMovieTitle)


        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(searchResult[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val searchData = searchResult[position]
        holder.txtTitle.text = searchData.original_title
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${searchData.poster_path}")
            .apply(RequestOptions().transform(RoundedCorners(20))) // 20dp corner radius
            .into(holder.imgPoster)
        holder.txtTitle.isSelected=true
    }

    override fun getItemCount(): Int = searchResult.size
}
