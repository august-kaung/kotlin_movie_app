package com.example.androidmovieapp.movie

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
import com.example.androidmovieapp.R
import com.example.androidmovieapp.adapter.ActorAdapter
import com.example.androidmovieapp.adapter.MovieAdapter
import com.example.androidmovieapp.controller.MovieController
import com.example.androidmovieapp.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)

        rvNowPlaying = binding.rvNowPlaying
        rvPopular = binding.rvPopular
        rvUpcoming = binding.rvUpcoming
        rvActors = binding.rvActors


        rvNowPlaying.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvUpcoming.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        popularAdapter = MovieAdapter(emptyList())
        nowPlayingAdapter = MovieAdapter(emptyList())
        upcomingAdapter = MovieAdapter(emptyList())
        actorAdapter = ActorAdapter(emptyList())

        rvPopular.adapter = popularAdapter
        rvNowPlaying.adapter = nowPlayingAdapter
        rvUpcoming.adapter = upcomingAdapter
        rvActors.adapter = actorAdapter

        // 🔥 Auto call API like initState
        loadAllMovies()


        setContentView(binding.root)

    }
    private fun loadAllMovies() {
        lifecycleScope.launch {
            try {
                val popular = controller.getPopularMovies()
                val nowPlaying = controller.getNowPlayingMovies()
                val upcoming = controller.getUpComingMovies()
                val actorsg = controller.getActors()

                popularAdapter.updateData(popular.results ?: emptyList())
                nowPlayingAdapter.updateData(nowPlaying.results ?: emptyList())
                upcomingAdapter.updateData(upcoming.results ?: emptyList())
                actorAdapter.updateData( actorsg.results ?: emptyList())

            } catch (e: Exception) {
                e.printStackTrace()
                // Optionally show a user-facing error message here
            }
        }
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