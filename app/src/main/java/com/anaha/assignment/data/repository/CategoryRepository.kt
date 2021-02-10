package com.anaha.assignment.data.repository

import com.anaha.assignment.data.api.ApiHelper
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getCategories(userKey: String) = apiHelper.getCategories(userKey)
}