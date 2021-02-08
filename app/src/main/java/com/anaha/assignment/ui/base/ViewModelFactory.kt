package com.anaha.assignment.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anaha.assignment.data.api.ApiHelper
import com.anaha.assignment.data.repository.CategoryRepository
import com.anaha.assignment.ui.category.viewmodel.CategoryViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(CategoryRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}