package com.anaha.assignment.data.api

import layout.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("categories")
    suspend fun getCategories(@Header("user-key") userKey : String) : CategoryResponse
}