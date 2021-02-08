package com.anaha.assignment.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getCategories(userKey: String) = apiService.getCategories(userKey)
}