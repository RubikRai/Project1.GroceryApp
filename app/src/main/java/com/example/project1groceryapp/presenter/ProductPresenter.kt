package com.example.project1groceryapp.presenter

import com.example.project1groceryapp.contracts.ProductContracts
import com.example.project1groceryapp.data.Product

class ProductPresenter(val productView: ProductContracts.ProductView,
val productModel: ProductContracts.ProductModel): ProductContracts.ProductPresenter,
ProductContracts.ProductModel.OnProductResponseListener
{
    override fun onResponseReceived(products: List<Product>) {
        productView.populateProductData(products)
    }

    override fun loadProductData(subCatId: Int) {
        productModel.getData(this, subCatId)
    }
}