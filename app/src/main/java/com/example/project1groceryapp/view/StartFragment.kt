package com.example.project1groceryapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project1groceryapp.R
import com.example.project1groceryapp.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frameLayoutStartPage.setOnClickListener{
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.framelayout_welcome_page_top,SignupFragment())?.addToBackStack("signupPage")
//                ?.commit()

                activity?.startActivity(Intent(activity, MainActivity::class.java))
        }
    }


}