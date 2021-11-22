package com.example.project1groceryapp.presenter

import com.example.project1groceryapp.contracts.SignUpModel
import com.example.project1groceryapp.contracts.SignUpView

class SignUpPresenter(val signUpView: SignUpView, val signUpModel: SignUpModel):SignUpModel.OnSignUpListener {

    fun registerUser(firstName: String, mobile: String, email: String, password: String){
        signUpModel.signUp(firstName, mobile, email, password, this)
    }

    override fun signUpResult(result: Boolean, message: String) {
        if(result){
            signUpView.signUpSuccess(message, true)
        }else{
            signUpView.signUpFailed()
        }
    }
}