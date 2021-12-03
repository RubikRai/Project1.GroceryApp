package com.example.project1groceryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1groceryapp.R
import com.example.project1groceryapp.adapter.CartAdapter
import com.example.project1groceryapp.contracts.CartContract
import com.example.project1groceryapp.contracts.ProductContracts
import com.example.project1groceryapp.data.CartProduct
import com.example.project1groceryapp.data.Product
import com.example.project1groceryapp.databinding.FragmentCartBinding
import com.example.project1groceryapp.model.CartDBHelper
import com.example.project1groceryapp.presenter.CartPresenter
import com.example.project1groceryapp.presenter.ProductPresenter
import kotlinx.android.synthetic.main.fragment_homepage.*

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    lateinit var adapter: CartAdapter


    var cart = ArrayList<CartProduct>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val cartPresenter = CartPresenter(this, CartContract(requireContext()))

        binding.recyclerviewCart.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.VERTICAL, false)

        val databaseHandler = createDataBaseHandler()
        cart = databaseHandler.getProducts()
        val subtotal = getSubTotal(cart)
        binding.textviewSubTotalSum.text = subtotal.toString()
        binding.textviewTaxSum.text = (subtotal * 0.10).toString()
        binding.textviewTotalSum.text = (subtotal + (subtotal * 0.10)).toString()
        adapter = CartAdapter(cart, requireContext())
        binding.recyclerviewCart.adapter = adapter

        binding.cartClose.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.framelayout_main_top,
                HomepageFragment(), "CloseCart")?.commit()
        }
    }

    private fun getSubTotal(productList: List<CartProduct>): Float{
        var total = 0.00
        for(i in productList){
            total += (i.Quantity * i.Price)
        }
        return total.toFloat()
    }

    private fun createDataBaseHandler(): CartDBHelper {
        return CartDBHelper(requireContext())
    }


}