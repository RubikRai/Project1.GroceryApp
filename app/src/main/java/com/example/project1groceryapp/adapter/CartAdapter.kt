package com.example.project1groceryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project1groceryapp.data.CartProduct
import com.example.project1groceryapp.databinding.ViewHolderCartBinding
import com.example.project1groceryapp.model.CartDBHelper
import com.example.project1groceryapp.view.CartViewHolder

class CartAdapter(var products: ArrayList<CartProduct>, context: Context):RecyclerView.Adapter<CartViewHolder>(){

    private var cartDBHelper: CartDBHelper = CartDBHelper(context)

    lateinit var onCartProductSelected:(CartProduct, Int) -> Unit
    fun setOnCartProductSelectedListener(listener: (CartProduct, Int) -> Unit){
        this.onCartProductSelected = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCartBinding.inflate(layoutInflater, parent, false)

        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindData(products[position])

        holder.itemView.setOnClickListener {
            if (this::onCartProductSelected.isInitialized) {
                onCartProductSelected(products[position], position)
            }
        }

        holder.binding.buttonRemove.setOnClickListener {
            cartDBHelper.deleteProduct(products[position])
            refreshData()
        }
    }


    override fun getItemCount() = products.size

    private fun refreshData(){
        products = cartDBHelper.getProducts()
        notifyDataSetChanged()
    }
}