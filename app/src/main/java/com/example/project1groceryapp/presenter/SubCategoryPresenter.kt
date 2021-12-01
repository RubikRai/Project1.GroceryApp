package com.example.project1groceryapp.presenter

import com.example.project1groceryapp.contracts.SubCategoryContracts
import com.example.project1groceryapp.data.SubCategory

class SubCategoryPresenter (val subCatView: SubCategoryContracts.SubCatView,
        val subCatModel: SubCategoryContracts.SubCatModel): SubCategoryContracts.SubCatPresenter,
        SubCategoryContracts.SubCatModel.OnSubCatResponseListener
{
    override fun onResponseReceived(subCategories: List<SubCategory>) {
        subCatView.populateSubCatData(subCategories)
    }

    override fun loadSubCatData(catId: Int) {
        subCatModel.getData(this, catId)
    }


}