package com.example.project1groceryapp.contracts

interface LoginView {
    fun loginSuccess(name: String)
    fun loginFailed()
}

interface LoginModel{
    fun login(email: String, password: String,listener: OnLoginListener)
    interface OnLoginListener{
        fun loginResult(result: Boolean, message: String)
    }
}

interface SignUpView{
    fun signUpSuccess(firstName: String, result: Boolean)
    fun signUpFailed()
}

interface SignUpModel{
    fun signUp(firstName: String, mobile: String, email: String, password: String, listener: OnSignUpListener)
    interface OnSignUpListener{
        fun signUpResult(result: Boolean, message: String)
    }
}