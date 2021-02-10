package com.anaha.assignment.data.api

import layout.CategoryResponse

interface ApiHelper {

    suspend fun getCategories(userKey: String) : CategoryResponse
}