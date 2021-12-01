package com.example.project1groceryapp.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.project1groceryapp.contracts.SubCategoryContracts
import com.example.project1groceryapp.data.ApiSubCatResponse
import com.example.project1groceryapp.data.SubCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SubCategoryResponseServices(private val context: Context): SubCategoryContracts.SubCatModel {

    override fun getData(listener: SubCategoryContracts.SubCatModel.OnSubCatResponseListener, catId: Int) {

        val requestQueue = Volley.newRequestQueue(this.context)
        val subCatRequest = StringRequest(
            Request.Method.GET,
            "https://grocery-second-app.herokuapp.com/api/subcategory/$catId",
            { response ->
                val gson = Gson()
                val typeInfo = object: TypeToken<ApiSubCatResponse>(){}
                val result: ApiSubCatResponse = gson.fromJson(response,typeInfo.type)
                listener.onResponseReceived(result.data)
                Log.d("Category", "${result.data.toString()}")
            },{
                    error ->
                Log.e("Error", "There is an error")
            }
        )
        requestQueue.add(subCatRequest)
    }
}