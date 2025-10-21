package com.example.androidmovieapp.apiService

import com.example.androidmovieapp.model.ActorResponse
import com.example.androidmovieapp.model.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
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
}
