package com.example.androidmovieapp.movie

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.androidmovieapp.R
import com.example.androidmovieapp.adapter.CastAdapter
import com.example.androidmovieapp.adapter.RelatedMovieAdapter
import com.example.androidmovieapp.controller.MovieController
import com.example.androidmovieapp.databinding.ActivityActorMovieBinding
import com.example.androidmovieapp.model.ActorDetail
import com.example.androidmovieapp.model.DetailResponse
import kotlinx.coroutines.launch

class ActorMovieActivity : AppCompatActivity() {
    lateinit var binding : ActivityActorMovieBinding
    lateinit var relatedMovieAdapter: RelatedMovieAdapter

    var controller = MovieController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityActorMovieBinding.inflate(layoutInflater)

        val rvActors = binding.rvRelatedMovies
        rvActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        relatedMovieAdapter = RelatedMovieAdapter(emptyList())
        rvActors.adapter = relatedMovieAdapter

        val actorId = intent.getIntExtra("actor_id", -1)
        if (actorId != -1) {
            loadAllDataOfActor(actorId)
        }




        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(resources.getColor(R.color.primaryColor))
        binding.toolbar.navigationIcon?.setTint(ContextCompat.getColor(this, R.color.primaryColor))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Actor Detail"
        setContentView(binding.root)

    }

    private fun bindActorData(actorDetail: ActorDetail) {
        binding.actorName.text = "Name : ${actorDetail.name}"
        binding.actorBd.text = "Date of Birth : ${actorDetail.birthday}"
        binding.actorBp.text = "Place of Birth : ${actorDetail.place_of_birth}"
        binding.actorBio.text = "Biography : ${actorDetail.biography}"
        binding.actorPopularity.text = "Popularity : ${actorDetail.popularity}"
        Glide.with(this).load("https://image.tmdb.org/t/p/w500${actorDetail.profile_path}")
            .into(binding.actorPosterDetail)
    }

    private fun loadAllDataOfActor(  actorId : Int) {
        lifecycleScope.launch {
            try {
                val actorData = controller.getActorDetails(actorId)
                val actorRelatedMovies = controller.getMoviesOfActors(actorId)

                bindActorData(actorData)
                relatedMovieAdapter.updateData(actorRelatedMovies.cast ?: emptyList())


            } catch (e: Exception) {
                e.printStackTrace()
                // Optionally show a user-facing error message here
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