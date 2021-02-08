package com.anaha.assignment.ui.category.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anaha.assignment.data.repository.CategoryRepository
import com.anaha.assignment.utils.Resource
import kotlinx.coroutines.Dispatchers

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    fun getCategories(userKey: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = categoryRepository.getCategories(userKey)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}