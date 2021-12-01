package com.example.project1groceryapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1groceryapp.R
import com.example.project1groceryapp.adapter.SubCategoryAdapter
import com.example.project1groceryapp.contracts.SubCategoryContracts
import com.example.project1groceryapp.data.Category
import com.example.project1groceryapp.data.SubCategory
import com.example.project1groceryapp.databinding.FragmentSubcategoryBinding
import com.example.project1groceryapp.model.SubCategoryResponseServices
import com.example.project1groceryapp.presenter.SubCategoryPresenter

class SubCategoryFragment:Fragment(), SubCategoryContracts.SubCatView {

    lateinit var binding: FragmentSubcategoryBinding
    lateinit var adapter: SubCategoryAdapter
    var catId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catId = arguments?.getInt("categoryID") ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubcategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subcategoryPresenter = SubCategoryPresenter(this, SubCategoryResponseServices(requireContext()))
        subcategoryPresenter.loadSubCatData(catId)
        binding.recyclerviewSubcategory.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.VERTICAL,
        false)
        val imageUrl = arguments?.getString("categoryImage")
        adapter = SubCategoryAdapter("https://rjtmobile.com/grocery/images/category_chicken.jpg")
        binding.recyclerviewSubcategory.adapter = adapter


        binding.imageviewArrowBack.setOnClickListener{
            requireActivity().onBackPressed()
        }

    }

    override fun populateSubCatData(subCategories: List<SubCategory>) {
        adapter.updateSubCategory(subCategories)

        adapter.setOnSubCategorySelectedListener{subCategory, position ->
            val bundle = Bundle()
            bundle.putInt("categoryId", subCategory.catId)
            bundle.putInt("subcategoryID", subCategory.subId)
            bundle.putString("subcategoryName", subCategory.subName)

            val productFragment = ProductFragment()
            productFragment.arguments = bundle
            binding.root.removeAllViews()

            activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.framelayout_main_top,
                productFragment)?.addToBackStack("BackToSubCategory")?.commit()

        }
    }
}