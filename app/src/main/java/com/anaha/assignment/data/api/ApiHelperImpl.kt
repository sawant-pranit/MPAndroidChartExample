package com.anaha.assignment.data.api

import layout.CategoryResponse
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService : ApiService) : ApiHelper{
    override suspend fun getCategories(userKey: String): CategoryResponse = apiService.getCategories(userKey)

}