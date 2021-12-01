package com.example.project1groceryapp.view

import androidx.recyclerview.widget.RecyclerView
import com.example.project1groceryapp.data.Product
import com.example.project1groceryapp.databinding.ViewHolderProductBinding
import com.squareup.picasso.Picasso

class ProductViewHolder(val binding: ViewHolderProductBinding):RecyclerView.ViewHolder(binding.root) {

    fun bindData(product: Product){
        binding.textviewProductName.text = product.productName
        val imageUrl ="https://rjtmobile.com/grocery/images/"
        Picasso.get()
            .load(imageUrl)
            .into(binding.imageviewProduct)
    }

}