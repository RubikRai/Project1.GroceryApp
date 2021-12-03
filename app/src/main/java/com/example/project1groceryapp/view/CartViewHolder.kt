package com.example.project1groceryapp.view

import androidx.recyclerview.widget.RecyclerView
import com.example.project1groceryapp.R
import com.example.project1groceryapp.data.CartProduct
import com.example.project1groceryapp.databinding.ViewHolderCartBinding
import com.squareup.picasso.Picasso

class CartViewHolder(val binding: ViewHolderCartBinding):RecyclerView.ViewHolder(binding.root) {

    fun bindData(product: CartProduct){
        binding.textviewProductName.text = product.Name
        binding.textviewQuantityNumber.text = product.Quantity.toString()

        Picasso.get()
            .load("https://rjtmobile.com/grocery/images/" + product.Image)
            .placeholder(R.drawable.ic_cart)
            .into(binding.imageviewProduct)
    }

}