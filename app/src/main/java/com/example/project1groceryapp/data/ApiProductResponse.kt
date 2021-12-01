package com.example.project1groceryapp.data

data class ApiProductResponse(
    val count: Int,
    val `data`: List<Product>,
    val error: Boolean
)

data class Product(
    val __v: Int,
    val _id: String,
    val catId: Int,
    val created: String,
    val description: String,
    val image: String,
    val mrp: Int,
    val position: Int,
    val price: Float,
    val productName: String,
    val quantity: Int,
    val status: Boolean,
    val subId: Int,
    val unit: String
)