package com.anaha.assignment.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anaha.assignment.R
import kotlinx.android.synthetic.main.item_category.view.*
import layout.Category

class CategoryAdapter(private val categories: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category) {
            itemView.apply {
                txtvCategoryId.text = this.context.getString(R.string.placeholder_id, category.categories.id)
                txtvCategoryTitle.text = this.context.getString(R.string.placeholder_category_name, category.categories.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    fun addCategory(categoryList: List<Category>) {
        this.categories.apply {
            clear()
            addAll(categoryList)
        }

    }
}