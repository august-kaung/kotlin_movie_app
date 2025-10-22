package com.example.androidmovieapp.model

data class MovieOfActorResponse(
    val cast: List<CastX>,
    val crew: List<CrewX>,
    val id: Int
)