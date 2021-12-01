package com.example.project1groceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project1groceryapp.data.Category
import com.example.project1groceryapp.databinding.ViewHolderHomePageBinding
import com.example.project1groceryapp.view.HomePageViewHolder

class CategoryAdapter(): RecyclerView.Adapter<HomePageViewHolder>() {

    private var category: List<Category> = listOf()
    private lateinit var onCategorySelected:(Category, Int) -> Unit

    fun setOnCategorySelectedListener(listener:(Category,Int) -> Unit) {
        this.onCategorySelected = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderHomePageBinding.inflate(layoutInflater, parent, false)

        return HomePageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) {
        val category = category[position]
        holder.bindData(category)

        holder.itemView.setOnClickListener {
            if (this::onCategorySelected.isInitialized) {
                onCategorySelected(category, position)
            }
        }
    }

    override fun getItemCount() = category.size

    fun updateCategory(catList:List<Category>){
        this.category = catList
        this.notifyDataSetChanged()
    }
}