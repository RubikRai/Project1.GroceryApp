package com.example.project1groceryapp.presenter


import com.example.project1groceryapp.contracts.LoginModel
import com.example.project1groceryapp.contracts.LoginView
import com.example.project1groceryapp.model.AuthenticationNetworkService

class LoginPresenter(val loginView: LoginView, val loginModel: LoginModel):LoginModel.OnLoginListener{

    fun loginUser(email: String, password: String){
        loginModel.login(email,password,this)
    }

    override fun loginResult(result: Boolean, message: String) {
        if(result){
            loginView.loginSuccess(message)
        }else{
            loginView.loginFailed()
        }
    }
}