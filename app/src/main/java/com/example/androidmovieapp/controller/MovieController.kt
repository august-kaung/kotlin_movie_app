package com.example.androidmovieapp.controller

import com.example.androidmovieapp.model.ActorDetail
import com.example.androidmovieapp.model.ActorResponse
import com.example.androidmovieapp.model.CastDetails
import com.example.androidmovieapp.model.DetailResponse
import com.example.androidmovieapp.model.MovieOfActorResponse
import com.example.androidmovieapp.model.MovieResponse
import com.example.androidmovieapp.model.SearchResponse
import com.example.app.controller.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieController {
    private val apiKey =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NmNjMDNhMTI4MjhlNmEyZDAzODc1MjRkNzBjNzQ2YyIsIm5iZiI6MTc2MTAyMDY5NS45MzkwMDAxLCJzdWIiOiI2OGY3MGIxN2IzNDZiZTE4Yjg4ZjhkYjUiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.M2Im_9LxGLg22vOI0N3Pzszo9LPLB9Ckw0DtJBS0Tpc"
    private val accept = "application/json"
    private val language = "en-US"
    private val page = 1


    suspend fun getPopularMovies(page: Int = 1): MovieResponse {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.getPopularMovies(
                apiKey = apiKey, accept = accept, language = language, page = page
            )
        }
    }

    suspend fun getUpComingMovies(page: Int = 1): MovieResponse {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.getUpcomingMovies(
                apiKey = apiKey, accept = accept, language = language, page = page
            )
        }
    }

    suspend fun getNowPlayingMovies(page: Int = 1): MovieResponse {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.getNowPlayingMovies(
                apiKey = apiKey, accept = accept, language = language, page = page
            )
        }
    }

    suspend fun getActors(page: Int = 1): ActorResponse {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.getActors(
                apiKey = apiKey, accept = accept, language = language, page = page
            )
        }
    }
    suspend fun getMovieDetails(movieId: Int): DetailResponse {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.getMovieDetail(
                apiKey = apiKey, accept = accept, language = language,  movieId = movieId
            )
        }
    }
    suspend fun getCasts(movieId: Int): CastDetails {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.getMovieCredits(
                apiKey = apiKey, accept = accept, language = language, movieId = movieId
            )
        }
    }

    suspend fun getSearchResult(query: String): SearchResponse {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.getSearchData(
                apiKey = apiKey, accept = accept, language = language, query = query
            )
        }
    }
    suspend fun getMoviesOfActors(actorId: Int): MovieOfActorResponse {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.getMovieOfActor(
                apiKey = apiKey, accept = accept, language = language, actorId = actorId
            )
        }
    }
    suspend fun getActorDetails(actorId: Int): ActorDetail {
        return withContext(Dispatchers.IO) {
            RetrofitClient.instance.getActorDetail(
                apiKey = apiKey, accept = accept, language = language, actorId = actorId
            )
        }
    }



}