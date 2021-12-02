package com.example.project1groceryapp.contracts

import com.example.project1groceryapp.data.CheckOut

interface CartContract {

    interface CartView{
        fun setData()
    }

    interface Presenter{
        fun postOrder(orderData: CheckOut)
    }
}