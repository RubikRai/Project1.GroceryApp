package com.example.project1groceryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1groceryapp.R
import com.example.project1groceryapp.adapter.CategoryAdapter
import com.example.project1groceryapp.contracts.CategoryContract
import com.example.project1groceryapp.data.Category
import com.example.project1groceryapp.databinding.FragmentHomepageBinding
import com.example.project1groceryapp.model.CategoryResponseService
import com.example.project1groceryapp.presenter.CategoryPresenter

class HomepageFragment: Fragment(), CategoryContract.CatView {
    //puto error

    lateinit var binding: FragmentHomepageBinding
    lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomepageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




/*
        binding.nav_view.setNavigationItemSelectedListener {
            menuItem.isCheckable = true
            when(menuItem.itemId){
                R.id.user_name -> Toast.makeText(this, "Clicked on user name", Toast.LENGTH_SHORT).show()

                R.id.profile -> Toast.makeText(this, "Clicked on profile", Toast.LENGTH_SHORT).show()

                R.id.shop -> Toast.makeText(this, "Clicked on shop", Toast.LENGTH_SHORT).show()

                R.id.my_order -> Toast.makeText(this, "Clicked on My order", Toast.LENGTH_SHORT).show()

                R.id.logout -> Toast.makeText(this, "Clicked on logout", Toast.LENGTH_SHORT).show()
            }
            true
        }
 */


        val categoryPresenter = CategoryPresenter(this,CategoryResponseService(requireContext()))
        categoryPresenter.loadCatData()
        binding.recyclerviewCategory.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,
            false)
            adapter = CategoryAdapter()
            binding.recyclerviewCategory.adapter = adapter
    }
/*
    override fun onSupportNavigateUp():Boolean{
        binding.drawerlayoutHomepage.openDrawer(binding.navView)
        return true
    }

    override fun onBackPressed(){
        if (binding.drawerlayoutHomepage.openDrawer(GravityCompat.START)){
            binding.drawerlayoutHomepage.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

 */

    override fun populateCatData(categories: List<Category>) {

        adapter.updateCategory(categories)

        adapter.setOnCategorySelectedListener{category, position ->
            val bundle = Bundle()
            val imageUrl = "https://rjtmobile.com/grocery/images/${category.catImage}"
            bundle.putInt("categoryID", category.catId)
            bundle.putString("categoryName", category.catName)
            bundle.putString("categoryImage", imageUrl)


            val subCategoryFragment = SubCategoryFragment()
            subCategoryFragment.arguments = bundle
            binding.root.removeAllViews()

            activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.framelayout_main_top,
                subCategoryFragment)?.addToBackStack("BacktoHome")?.commit()


        }
        binding.recyclerviewCategory.adapter = adapter
    }


}