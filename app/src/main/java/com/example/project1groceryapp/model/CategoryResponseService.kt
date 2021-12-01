package com.example.project1groceryapp.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.project1groceryapp.contracts.CategoryContract
import com.example.project1groceryapp.data.ApiCatResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryResponseService(private val context: Context): CategoryContract.CatModel {

    override fun getData(listener: CategoryContract.CatModel.OnCatResponseListener){

        val requestQueue = Volley.newRequestQueue(this.context)
        val catRequest = StringRequest(
            Request.Method.GET,
            "https://grocery-second-app.herokuapp.com/api/category",
            {
                response ->
                val gson = Gson()
                val typeInfo = object: TypeToken<ApiCatResponse>(){}
                val result:ApiCatResponse = gson.fromJson(response,typeInfo.type)
                listener.onResponseReceived(result.data)
                Log.d("Category", "${result.data.toString()}")
            },{
                error ->
                Log.e("Error", "There is an error")
            }
        )
        requestQueue.add(catRequest)
    }
}