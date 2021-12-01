package com.example.project1groceryapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.project1groceryapp.R
import com.example.project1groceryapp.databinding.ActivitySplashScreenUsingAnimationBinding

class SplashScreenUsingAnimation : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenUsingAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenUsingAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animation = AnimationUtils.loadAnimation(this,R.anim.translate)
        binding.apply {
            framelayoutWelcomePageTop.startAnimation(animation)
        }

        val fragStart = StartFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout_welcome_page_bottom, fragStart,"Login")
            .addToBackStack("backStart").commit()

    }
}