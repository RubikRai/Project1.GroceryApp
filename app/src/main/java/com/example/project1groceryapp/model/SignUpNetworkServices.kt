package com.example.project1groceryapp.model

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.project1groceryapp.contracts.SignUpModel
import org.json.JSONObject

class SignUpNetworkServices(private val context: Context):SignUpModel {

    override fun signUp(
        firstName: String,
        mobile: String,
        email: String,
        password: String,
        listener: SignUpModel.OnSignUpListener
    ) {
        var userDetails = JSONObject()
        userDetails.put("firstName", firstName)
        userDetails.put("mobile", mobile)
        userDetails.put("email", email)
        userDetails.put("password", password)

        val requestQueue = Volley.newRequestQueue(this.context)

        val pd = ProgressDialog(this.context)
        pd.setMessage("Please wait while we register your details>>>")
        pd.setCancelable(false)

        val request = JsonObjectRequest(
            Request.Method.POST,
            "https://grocery-second-app.herokuapp.com/api/auth/register",
            userDetails,
            {response ->
                val error = response.getBoolean("error")
                val message = response.getString("message")

                if(error) {
                    Toast.makeText(context,"Error: $message", Toast.LENGTH_LONG).show()
                }else {
//                    Toast.makeText(
//                        context, "Registration for $firstName is successful", Toast.LENGTH_LONG).show()
                    listener.signUpResult(true,"")
                }
                pd.dismiss()

            },
            {error ->
                Log.d("MainActivity", "${error.toString()}")
                Toast.makeText(context, "Error occured: ${error.toString()}", Toast.LENGTH_SHORT).show()
                pd.dismiss()
            }

        )
        requestQueue.add(request)
        pd.show()
    }
}