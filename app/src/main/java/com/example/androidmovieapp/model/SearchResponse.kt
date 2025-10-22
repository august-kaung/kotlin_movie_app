package com.example.androidmovieapp.model

data class SearchResponse(
    val page: Int,
    val results: List<ResultXX>,
    val total_pages: Int,
    val total_results: Int
)