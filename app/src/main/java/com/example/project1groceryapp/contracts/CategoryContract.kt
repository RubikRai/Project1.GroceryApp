package com.example.project1groceryapp.contracts

import com.example.project1groceryapp.data.Category

interface CategoryContract {

    interface CatModel{
        fun getData(listener: OnCatResponseListener)
        interface OnCatResponseListener{
            fun onResponseReceived(categories: List<Category>)
        }
    }


    interface CatPresenter{
        fun loadCatData()
    }

    interface CatView{
        //abstract val FragmentHomepageBinding: Any

        fun populateCatData(categories: List<Category>)
    }
}