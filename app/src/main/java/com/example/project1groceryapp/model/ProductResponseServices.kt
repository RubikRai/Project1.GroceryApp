package com.example.project1groceryapp.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.project1groceryapp.contracts.ProductContracts
import com.example.project1groceryapp.data.ApiProductResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductResponseServices(private val context: Context): ProductContracts.ProductModel {
    override fun getData(
        listener: ProductContracts.ProductModel.OnProductResponseListener,
        subCatId: Int
    ) {
        val requestQueue = Volley.newRequestQueue(this.context)
        Log.d("website", "https://grocery-second-app.herokuapp.com/api/products/sub/$subCatId")
        val productRequest = StringRequest(
            Request.Method.GET,
            "https://grocery-second-app.herokuapp.com/api/products/sub/$subCatId",
            { response ->
                val gson = Gson()
                val typeInfo = object: TypeToken<ApiProductResponse>(){}
                val result: ApiProductResponse = gson.fromJson(response,typeInfo.type)
                listener.onResponseReceived(result.data)
                Log.d("Product", "${result.data.toString()}")

            },{
                error ->
                Log.e("Error", "There is an error")
            }
        )
        requestQueue.add(productRequest)
    }

}