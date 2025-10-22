package com.example.androidmovieapp.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidmovieapp.R
import com.example.androidmovieapp.adapter.GridItemAdapter
import com.example.androidmovieapp.controller.MovieController
import com.example.androidmovieapp.databinding.ActivitySearchBinding
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    private lateinit var adapter: GridItemAdapter
    var controller = MovieController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(LayoutInflater.from(this))
        val recyclerView = binding.gvSearch
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = GridItemAdapter(emptyList()) { selectedMovie ->
            val intent = android.content.Intent(this, DetailActivity::class.java)
            intent.putExtra("movie_id", selectedMovie.id)
            startActivity(intent)
        }

        binding.txtvSearch.setOnEditorActionListener { v, actionId, event ->
            val query = binding.txtvSearch.text.toString().trim()
            val intent = android.content.Intent(this, DetailActivity::class.java)
            searchMovies(query,intent)
            binding.txtvSearch.clearFocus()
            true

        }
        binding.navigateBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }




        setContentView(binding.root)

    }

    private fun searchMovies(query: String , intent : Intent) {
        lifecycleScope.launch {
            try {
                val response = controller.getSearchResult(query)
                adapter = GridItemAdapter(response.results) { selectedMovie ->

                    intent.putExtra("movie_id", selectedMovie.id)
                    startActivity(intent)
                }
                binding.gvSearch.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@SearchActivity, "Search failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

