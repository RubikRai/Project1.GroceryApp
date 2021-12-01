package com.example.project1groceryapp.contracts

import com.example.project1groceryapp.data.Product

class ProductContracts {

    interface ProductModel{
        fun getData(listener: OnProductResponseListener, subCatId: Int)
        interface OnProductResponseListener{
            fun onResponseReceived(products: List<Product>)
        }
    }

    interface ProductPresenter{
        fun loadProductData(subCatId: Int)
    }

    interface ProductView{
        fun populateProductData(products: List<Product>)
    }

}