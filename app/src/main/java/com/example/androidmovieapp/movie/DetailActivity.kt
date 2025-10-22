package com.example.androidmovieapp.movie

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidmovieapp.R
import com.example.androidmovieapp.adapter.ActorAdapter
import com.example.androidmovieapp.adapter.CastAdapter
import com.example.androidmovieapp.controller.MovieController
import com.example.androidmovieapp.databinding.ActivityDetailBinding
import com.example.androidmovieapp.model.DetailResponse
import com.example.androidmovieapp.model.MovieResult
import kotlinx.coroutines.launch
import androidx.core.graphics.toColorInt

class DetailActivity : AppCompatActivity() {
    private lateinit var scrollView: ScrollView
    private lateinit var castAdapter: CastAdapter
    var movieTitle = ""
    var imagPoster = ""

    private val controller = MovieController()
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        scrollView = binding.scrollView

        val rvActors = binding.rvActorsOfDetail
        rvActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        castAdapter = CastAdapter(emptyList())
        rvActors.adapter = castAdapter

        val movieId = intent.getIntExtra("movie_id", -1)
        if (movieId != -1) {
            fetchMovieDetail(movieId)
        }
        binding.moviePosterDetail.setOnClickListener {
            showPosterDialog("https://image.tmdb.org/t/p/w500${imagPoster}")
        }



        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.primaryColor))
        binding.toolbar.navigationIcon?.setTint(ContextCompat.getColor(this, R.color.primaryColor))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = movieTitle

        setContentView(binding.root)

    }

    private fun bindMovieData(detail: DetailResponse) {
        binding.txtTitle.text = "Title : ${detail.title}"
        binding.txtStatus.text = "Status : ${detail.status}"
        binding.txtGenres.text = "Genres : ${detail.genres.joinToString(", ") { it.name }}"
        binding.txtCountries.text =
            "Countries : ${detail.production_countries.joinToString(", ") { it.name }}"
        binding.txtReleaseDate.text = "Release Date : ${detail.release_date}"
        binding.txtPopularity.text = "Popularity : ${detail.popularity}"
        binding.txtVoteCount.text = "Votes: ${detail.vote_count}"
        binding.txtVoteAverage.text = "Rating: ${detail.vote_average}"
        binding.txtRuntime.text = "Runtime: ${detail.runtime} min"
        binding.txtOverview.text = detail.overview

        Glide.with(this).load("https://image.tmdb.org/t/p/w500${detail.backdrop_path}")
            .into(binding.detailPosterView)

        val posterUrl = "https://image.tmdb.org/t/p/w500${detail.poster_path}"
        imagPoster = posterUrl
        Glide.with(this).load(posterUrl).placeholder(R.drawable.app_icon)
            .into(binding.moviePosterDetail)
    }

    private fun showPosterDialog(imageUrl: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_full_poster)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
        )

        val fullPoster = dialog.findViewById<ImageView>(R.id.fullPosterImageView)
        Glide.with(this).load(imageUrl).placeholder(R.drawable.app_icon).into(fullPoster)

        dialog.show()
    }


    private fun fetchMovieDetail(movieId: Int) {


        lifecycleScope.launch {
            try {
                val detail = controller.getMovieDetails(movieId)
                val credits = controller.getCasts(movieId)
                supportActionBar?.title = detail.title

                bindMovieData(detail)
                castAdapter.updateData(credits.cast ?: emptyList())
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@DetailActivity, "Failed to load movie", Toast.LENGTH_SHORT)
                    .show()
            } finally {

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true

        }
        return super.onOptionsItemSelected(item)
    }
}