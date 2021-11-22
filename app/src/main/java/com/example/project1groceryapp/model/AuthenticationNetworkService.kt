package com.example.project1groceryapp.model

import android.content.Context
import android.graphics.ColorSpace
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.project1groceryapp.contracts.LoginModel
import org.json.JSONObject
import java.lang.Exception

class AuthenticationNetworkService(private val context: Context):LoginModel {

    override fun login(email: String, password: String, listener:LoginModel.OnLoginListener){

        val userLoginDetail = JSONObject()
        userLoginDetail.put("email", email)
        userLoginDetail.put("password", password)

        val requestQueue = Volley.newRequestQueue(this.context)

        val request = JsonObjectRequest(
            Request.Method.POST,
            "https://grocery-second-app.herokuapp.com/api/auth/login",
            userLoginDetail,
            {response ->
                        listener.loginResult(true, response.getString("token"))
            },
            {error ->
                listener.loginResult(false,"Error in login")
            }
        )
        requestQueue.add(request)
    }

}