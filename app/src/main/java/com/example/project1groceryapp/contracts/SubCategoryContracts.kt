package com.example.project1groceryapp.contracts

import com.example.project1groceryapp.data.SubCategory

class SubCategoryContracts {

    interface SubCatModel{
        fun getData(listener: OnSubCatResponseListener, catId: Int)
        interface OnSubCatResponseListener{
            fun onResponseReceived(subCategories: List<SubCategory>)
        }
    }

    interface SubCatPresenter{
        fun loadSubCatData(catId:Int)
    }

    interface SubCatView{
        fun populateSubCatData(subCategories: List<SubCategory>)
    }

}