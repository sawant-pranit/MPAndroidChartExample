package com.anaha.assignment.ui.home.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.anaha.assignment.R
import com.anaha.assignment.ui.category.view.CategoryListActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    companion object {
        fun getIntent(context : Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primaryTextColor)
        setContentView(R.layout.activity_home)
        setupTabLayout()
        setProgressData()
    }

    private fun setupTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Sat"))
        tabLayout.addTab(tabLayout.newTab().setText("Sun"))
        tabLayout.addTab(tabLayout.newTab().setText("Mon"))
        tabLayout.addTab(tabLayout.newTab().setText("Tue"))

    }

    private fun setProgressData() {
        circular_progress1.maxProgress = 100.0
        circular_progress1.setCurrentProgress(80.0)

        circular_progress2.maxProgress = 100.0
        circular_progress2.setCurrentProgress(80.0)

        circular_progress3.maxProgress = 100.0
        circular_progress3.setCurrentProgress(80.0)
    }
}