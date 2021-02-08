package com.anaha.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anaha.assignment.ui.analytics.view.AnalyticsActivity
import com.anaha.assignment.ui.category.view.CategoryListActivity
import com.anaha.assignment.ui.home.view.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnActionObj2.setOnClickListener {
            startActivity(CategoryListActivity.getIntent(this))
        }

        btnActionObj1Part1.setOnClickListener {
            startActivity(AnalyticsActivity.getIntent(this))
        }

        btnActionObj1Part2.setOnClickListener {
            startActivity(HomeActivity.getIntent(this))
        }
    }
}