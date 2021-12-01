package com.example.project1groceryapp.view

import androidx.recyclerview.widget.RecyclerView
import com.example.project1groceryapp.data.Category
import com.example.project1groceryapp.databinding.ViewHolderHomePageBinding
import com.squareup.picasso.Picasso

class HomePageViewHolder(val binding: ViewHolderHomePageBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindData(category: Category) {
        binding.textviewCategoryName.text = category.catName
        val imageUrl = "https://rjtmobile.com/grocery/images/${category.catImage}"
        Picasso.get()
                .load(imageUrl)
                .into(binding.imageviewCategory)

    }
}