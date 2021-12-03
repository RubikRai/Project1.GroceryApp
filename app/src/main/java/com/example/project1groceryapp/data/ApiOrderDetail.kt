package com.example.project1groceryapp.data

data class ApiOrderDetail(
    val orderSummary: OrderSummaryRequest,
    val payment: PaymentRequest,
    val products: List<ProductRequest>,
    val shippingAddress: ShippingAddressRequest,
    val userId: String
)


data class OrderSummaryRequest(
    val deliveryCharges: Int,
    val discount: Int,
    val ourPrice: Int,
    val totalAmount: Int
)


data class PaymentRequest(
    val paymentMode: String
)

data class ProductRequest(
    val id: String,
    val price: Int,
    val productName: String,
    val quantity: Int
)

data class ShippingAddressRequest(
    val city: String,
    val houseNo: String,
    val pincode: Int,
    val streetName: String,
    val type: String
)
val orderSummary: OrderSummaryRequest = OrderSummaryRequest(1, 1 , 2, 1)
val paymentRequest = PaymentRequest("Cash")
val productRequest = ProductRequest("abc123", 1, "Potato", 1)
val shippingAddressRequest = ShippingAddressRequest("Mankato", "123", 123465, "", "")
val productList = arrayListOf<ProductRequest>(productRequest)
val userOrder: ApiOrderDetail = ApiOrderDetail(orderSummary, paymentRequest, productList, shippingAddressRequest, "NareyIsABitch")