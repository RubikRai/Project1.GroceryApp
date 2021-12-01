package com.example.project1groceryapp.view


import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.project1groceryapp.data.SubCategory
import com.example.project1groceryapp.databinding.ViewHolderSubCategoryBinding
import com.squareup.picasso.Picasso

class SubCategoryViewHolder(val binding: ViewHolderSubCategoryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(subCategory: SubCategory, categoryImage: String){
            binding.textviewSubcategoryName.text = subCategory.subName
            Picasso.get()
                .load(categoryImage)
                .into(binding.imageviewSubcategory)
        }
}