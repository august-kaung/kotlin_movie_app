package com.example.androidmovieapp.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmovieapp.ProfileActivity
import com.example.androidmovieapp.R
import com.example.androidmovieapp.adapter.ActorAdapter
import com.example.androidmovieapp.adapter.MovieAdapter
import com.example.androidmovieapp.auth.LogIn
import com.example.androidmovieapp.controller.MovieController
import com.example.androidmovieapp.databinding.ActivityHomeBinding
import com.example.androidmovieapp.model.ActorDetail
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private val controller = MovieController()

    private lateinit var rvNowPlaying: RecyclerView
    private lateinit var rvPopular: RecyclerView
    private lateinit var rvUpcoming: RecyclerView
    private lateinit var rvActors: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var popularAdapter: MovieAdapter
    private lateinit var nowPlayingAdapter: MovieAdapter
    private lateinit var upcomingAdapter: MovieAdapter
    private lateinit var actorAdapter: ActorAdapter

    private var currentPage = 1
    private var totalPages = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)

        rvNowPlaying = binding.rvNowPlaying
        rvPopular = binding.rvPopular
        rvUpcoming = binding.rvUpcoming
        rvActors = binding.rvActors


        rvNowPlaying.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvUpcoming.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        popularAdapter = MovieAdapter(mutableListOf()) { selectedMovie ->
            val intent = android.content.Intent(this, DetailActivity::class.java)
            intent.putExtra("movie_id", selectedMovie.id)
            startActivity(intent)

        }
        nowPlayingAdapter = MovieAdapter(mutableListOf()) { selectedMovie ->
            val intent = android.content.Intent(this, DetailActivity::class.java)
            intent.putExtra("movie_id", selectedMovie.id)
            startActivity(intent)

        }
        upcomingAdapter = MovieAdapter(mutableListOf()) { selectedMovie ->
            val intent = android.content.Intent(this, DetailActivity::class.java)
            intent.putExtra("movie_id", selectedMovie.id)
            startActivity(intent)
        }
        actorAdapter = ActorAdapter(emptyList()) { selectedActor ->
            val intent = android.content.Intent(this, ActorMovieActivity::class.java)
            intent.putExtra("actor_id", selectedActor.id)
            startActivity(intent)

        }

        rvPopular.adapter = popularAdapter
        rvNowPlaying.adapter = nowPlayingAdapter
        rvUpcoming.adapter = upcomingAdapter
        rvActors.adapter = actorAdapter

        loadAllMovies()
        loadPopularMovies(page = currentPage)

        setupPagination()

        binding.imgProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.imgSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)
    }

    private fun loadPopularMovies(page: Int) {
        if (isLoading) return
        isLoading = true

        lifecycleScope.launch {
            try {
                val response = controller.getPopularMovies(page)
                totalPages = response.total_pages ?: 1

                if (page == 1) {
                    popularAdapter.updateData(response.results.toMutableList() ?: mutableListOf())
                } else {
                    popularAdapter.appendData(response.results.toMutableList() ?: mutableListOf())
                }

                currentPage++
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    private fun loadAllMovies() {
        lifecycleScope.launch {
            try {

                val nowPlaying = controller.getNowPlayingMovies()
                val upcoming = controller.getUpComingMovies()
                val actorsg = controller.getActors()


                nowPlayingAdapter.updateData(nowPlaying.results.toMutableList() ?: mutableListOf())
                upcomingAdapter.updateData(upcoming.results.toMutableList() ?: mutableListOf())
                actorAdapter.updateData(actorsg.results ?: emptyList())

            } catch (e: Exception) {
                e.printStackTrace()
                // Optionally show a user-facing error message here
            }
        }
    }

    private fun setupPagination() {
        rvPopular.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                val isAtEnd =
                    (visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 2 && firstVisibleItemPosition >= 0 && currentPage <= totalPages

                if (isAtEnd && !isLoading) {
                    loadPopularMovies(page = currentPage)
                }
            }
        })
    }

//    private fun loadAllMovies() {
//        lifecycleScope.launch {
//            try {
//                val popular = controller.getPopularMovies()
//                val nowPlaying = controller.getNowPlayingMovies()
//                val upcoming = controller.getUpComingMovies()
//
//                if (popular.isSuccessful) {
//                    popularAdapter.updateData(popular.body()?.results ?: emptyList())
//                }
//                if (nowPlaying.isSuccessful) {
//                    nowPlayingAdapter.updateData(nowPlaying.body()?.results ?: emptyList())
//                }
//                if (upcoming.isSuccessful) {
//                    upcomingAdapter.updateData(upcoming.body()?.results ?: emptyList())
//                }
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            } finally {
//            }
//        }
//    }
}