package com.example.project1groceryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1groceryapp.R
import com.example.project1groceryapp.adapter.ProductAdapter
import com.example.project1groceryapp.contracts.ProductContracts
import com.example.project1groceryapp.data.Product
import com.example.project1groceryapp.databinding.FragmentProductBinding
import com.example.project1groceryapp.model.CartDBHelper
import com.example.project1groceryapp.model.ProductResponseServices
import com.example.project1groceryapp.presenter.ProductPresenter


class ProductFragment : Fragment(), ProductContracts.ProductView {

    lateinit var binding: FragmentProductBinding
    lateinit var adapter: ProductAdapter
    var subCatId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subCatId = arguments?.getInt("subCategoryID") ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subCatId = arguments?.getInt("subcategoryID")!!
        val productPresenter = ProductPresenter(this, ProductResponseServices(requireContext()))
        productPresenter.loadProductData(subCatId)
        binding.recyclerviewProduct.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.VERTICAL,false)
        adapter = ProductAdapter(requireContext())
        binding.recyclerviewProduct.adapter = adapter

        binding.imageviewArrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun populateProductData(products: List<Product>) {
        adapter.updateProduct(products)

        adapter.setOnProductSelectedListener{product, position ->
            val bundle = Bundle()
            bundle.putInt("subcategoryID", product.subId)
        }
    }

}