package com.example.project1groceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project1groceryapp.data.SubCategory
import com.example.project1groceryapp.databinding.ViewHolderSubCategoryBinding
import com.example.project1groceryapp.view.SubCategoryViewHolder

class SubCategoryAdapter(val categoryImage: String): RecyclerView.Adapter<SubCategoryViewHolder>() {

    private var subCategory: List<SubCategory> = listOf()
    private lateinit var onSubCategorySelected:(SubCategory, Int) -> Unit

    fun setOnSubCategorySelectedListener(listener:(SubCategory, Int) -> Unit){
        this.onSubCategorySelected = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderSubCategoryBinding.inflate(layoutInflater, parent, false)
        return SubCategoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        val subCategory = subCategory[position]
        holder.bindData(subCategory, categoryImage)

        holder.itemView.setOnClickListener{
            if(this::onSubCategorySelected.isInitialized){
                onSubCategorySelected(subCategory, position)
            }
        }
    }

    override fun getItemCount() = subCategory.size

    fun updateSubCategory(subCatList: List<SubCategory>){
        this.subCategory = subCatList
        this.notifyDataSetChanged()
    }

}