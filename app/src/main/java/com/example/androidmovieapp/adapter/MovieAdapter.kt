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

class MovieAdapter(private var movies: MutableList<MovieResult>,private val onItemClick: (MovieResult) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View  ) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.imgMoviePoster)
        val txtTitle: TextView = itemView.findViewById(R.id.txtMovieTitle)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(movies[position])
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.txtTitle.text = movie.title
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(holder.imgPoster)
        holder.txtTitle.isSelected=true
    }

    fun updateData(newMovies: MutableList<MovieResult>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }
    fun appendData(newMovies: List<MovieResult>) {
        val startPosition = movies.size
        movies.addAll(newMovies)
        notifyItemRangeInserted(startPosition, newMovies.size)
    }
}
