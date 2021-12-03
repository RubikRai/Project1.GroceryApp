package com.example.project1groceryapp.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.project1groceryapp.contracts.CartContract
import com.example.project1groceryapp.data.CheckOut
import com.example.project1groceryapp.view.CartFragment
import org.json.JSONObject

class CartPresenter(val cartFragment: CartFragment, val context: Context): CartContract.Presenter {

    lateinit var requestQueue: RequestQueue

    override fun postOrder(orderData: CheckOut) {
//        val url = "https://grocery-second-app.herokuapp.com/api/orders"
//        val mapper = jacksonObjectMapper()
//        val params = JSONObject(mapper.writeValueAsString(orderData))
//
//        requestQueue = Volley.newRequestQueue(context)
//
//        val request = JsonObjectRequest(
//            Request.Method.POST,
//            url,
//            params,
//            {response: JSONObject ->
//                val msg = response.getString("message")
//                Log.d("Order", "$msg")
//                Toast.makeText(context, "Order has been placed successfully! Thank you for your business!", Toast.LENGTH_LONG).show()
//                cartFragment.clearCart()
//            },
//            { error ->
////                Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
//                Log.d("Order", "$error")
//            }
//        )
//        requestQueue.add(request)
    }

}