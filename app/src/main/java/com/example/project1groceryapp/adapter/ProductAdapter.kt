package com.example.project1groceryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project1groceryapp.data.Product
import com.example.project1groceryapp.databinding.ViewHolderProductBinding
import com.example.project1groceryapp.model.CartDBHelper
import com.example.project1groceryapp.view.ProductViewHolder

class ProductAdapter(private val context: Context):RecyclerView.Adapter<ProductViewHolder>() {

    private var product: List<Product> = listOf()
    private lateinit var onProductSelected: (Product, Int) -> Unit
    private var cartDBHelper: CartDBHelper = CartDBHelper(context)

    fun setOnProductSelectedListener(listener:(Product, Int) -> Unit) {
        this.onProductSelected = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = product[position]
        holder.bindData(product)
        holder.binding.buttonAddProductToCart.setOnClickListener {
            cartDBHelper.addProduct(product, 1)
        }
        holder.itemView.setOnClickListener{
            if(this::onProductSelected.isInitialized){
                onProductSelected(product, position)
            }
        }
    }

    override fun getItemCount() = product.size

    fun updateProduct(productList: List<Product>){
        this.product = productList
        this.notifyDataSetChanged()
    }

}