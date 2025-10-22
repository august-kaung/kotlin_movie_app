package com.example.androidmovieapp.apiService

import com.example.androidmovieapp.model.ActorDetail
import com.example.androidmovieapp.model.ActorResponse
import com.example.androidmovieapp.model.CastDetails
import com.example.androidmovieapp.model.DetailResponse
import com.example.androidmovieapp.model.MovieOfActorResponse
import com.example.androidmovieapp.model.MovieResponse
import com.example.androidmovieapp.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") apiKey: String,
        @Header("accept") accept: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Header("Authorization") apiKey: String,
        @Header("accept") accept: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Header("Authorization") apiKey: String,
        @Header("accept") accept: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("person/popular")
    suspend fun getActors(
        @Header("Authorization") apiKey: String,
        @Header("accept") accept: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): ActorResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Header("Authorization") apiKey: String,
        @Header("accept") accept: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): DetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Header("Authorization") apiKey: String,
        @Header("accept") accept: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): CastDetails

    @GET("search/movie")
    suspend fun getSearchData(
        @Header("Authorization") apiKey: String,
        @Header("accept") accept: String,
        @Query("query") query: String,
        @Query("language") language: String
    ): SearchResponse

    @GET("person/{actor_id}/movie_credits")
    suspend fun getMovieOfActor(
        @Header("Authorization") apiKey: String,
        @Header("accept") accept: String,
        @Path("actor_id") actorId: Int,
        @Query("language") language: String
    ): MovieOfActorResponse

    @GET("person/{person_id}")
    suspend fun getActorDetail(
        @Header("Authorization") apiKey: String,
        @Header("accept") accept: String,
        @Path("person_id") actorId: Int,
        @Query("language") language: String
    ): ActorDetail
}
