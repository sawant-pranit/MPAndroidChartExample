package com.anaha.assignment.data.repository

import com.anaha.assignment.data.api.ApiHelper

class CategoryRepository(private val apiHelper: ApiHelper) {
    suspend fun getCategories(userKey: String) = apiHelper.getCategories(userKey)
}