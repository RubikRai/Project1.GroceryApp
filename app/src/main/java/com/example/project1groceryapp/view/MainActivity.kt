package com.example.project1groceryapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.example.project1groceryapp.R
import com.example.project1groceryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = this.getSharedPreferences("UserManager", Context.MODE_PRIVATE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragSignUp = SignupFragment()
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_main_top,
            fragSignUp, "SignUp").addToBackStack("backToStart").commit()

        actionBarToggle = ActionBarDrawerToggle(this, binding.drawerlayoutHomepage, binding.toolbar, 0,0)
        binding.drawerlayoutHomepage.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> Toast.makeText(applicationContext, "This is user profile", Toast.LENGTH_SHORT).show()
                R.id.cart ->
                    supportFragmentManager.beginTransaction().replace(R.id.framelayout_main_top, CartFragment(), "GoToCart").commit()
                R.id.my_order -> Toast.makeText(applicationContext, "This is your order list", Toast.LENGTH_SHORT).show()
                R.id.logout -> showLogOutMessage()
            }
            true
        }

        binding.imageviewCart.setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.framelayout_main_top,
                CartFragment(),"OpenCart").addToBackStack("GoBacktoMenu").commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLogOutMessage(){
        val dialog = AlertDialog.Builder(this)
            .setTitle("Log Out")
            .setMessage("Are you sure you want to log out?")
            .setIcon(R.drawable.ic_info)
            .setNegativeButton("Yes")
            { dialog, which ->
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_main_top, LoginFragment(), "BacktoLogin").commit()
                sharedPreferences.edit().putBoolean("LoggedIn", false).commit()
            }
            .setPositiveButton("No")
            { dialog, which ->
                dialog.dismiss()
            }
            .create().apply {
                show()
            }
    }

}