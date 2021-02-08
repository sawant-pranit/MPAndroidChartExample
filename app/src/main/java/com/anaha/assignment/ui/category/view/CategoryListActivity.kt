package com.anaha.assignment.ui.category.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anaha.assignment.R.layout
import com.anaha.assignment.data.api.ApiHelper
import com.anaha.assignment.data.api.RetrofitBuilder
import com.anaha.assignment.ui.base.ViewModelFactory
import com.anaha.assignment.ui.category.adapter.CategoryAdapter
import com.anaha.assignment.ui.category.viewmodel.CategoryViewModel
import com.anaha.assignment.utils.Constants
import com.anaha.assignment.utils.Status
import kotlinx.android.synthetic.main.activity_category_list.*
import layout.Category

class CategoryListActivity  : AppCompatActivity(){

    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter

    companion object {
        fun getIntent(context : Context) = Intent(context, CategoryListActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_category_list)
        setupViewModel()
        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getCategories(Constants.ZOMMATO_API_KEY).observe(this) { it ->
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        rcyvCategory.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { res ->
                            retrieveList(res.categories)
                        }
                    }
                    Status.ERROR -> {
                        rcyvCategory.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        rcyvCategory.visibility = View.GONE
                    }
                }

            }
        }
    }

    private fun retrieveList(categories: List<Category>) {
        adapter.apply {
            addCategory(categories)
            notifyDataSetChanged()
        }
    }

    private fun setupUI() {
        rcyvCategory.layoutManager = LinearLayoutManager(this)
        adapter = CategoryAdapter(arrayListOf())
        rcyvCategory.addItemDecoration(
            DividerItemDecoration(
                rcyvCategory.context,
                (rcyvCategory.layoutManager as LinearLayoutManager).orientation
            )
        )
        rcyvCategory.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(CategoryViewModel::class.java)
    }
}