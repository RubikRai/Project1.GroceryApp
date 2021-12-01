package com.example.project1groceryapp.presenter

import com.example.project1groceryapp.contracts.CategoryContract
import com.example.project1groceryapp.data.Category

class CategoryPresenter(val catView: CategoryContract.CatView,
                        val catModel:CategoryContract.CatModel): CategoryContract.CatPresenter,
                            CategoryContract.CatModel.OnCatResponseListener
{
    override fun loadCatData() {
        catModel.getData(this)
    }

    override fun onResponseReceived(categories: List<Category>) {
        catView.populateCatData(categories)
    }
}