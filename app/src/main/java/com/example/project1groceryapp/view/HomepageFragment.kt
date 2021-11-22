package com.example.project1groceryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.project1groceryapp.R
import com.example.project1groceryapp.databinding.FragmentHomepageBinding
import kotlinx.android.synthetic.main.activity_navigation_drawer.*
import kotlinx.android.synthetic.main.fragment_homepage.*


class HomepageFragment : Fragment() {

    lateinit var binding:FragmentHomepageBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomepageBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        toggle = ActionBarDrawerToggle(this, drawerlayout_homepage,R.string.open, R.string.close)
        drawerlayout_homepage.addDrawerListener(toggle)
        toggle.syncState()

 */
    }



}