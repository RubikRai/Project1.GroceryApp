package com.example.project1groceryapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.project1groceryapp.R
import com.example.project1groceryapp.databinding.ActivityNavigationDrawerBinding
import kotlinx.android.synthetic.main.activity_navigation_drawer.*

class NavigationDrawer : AppCompatActivity() {

    lateinit var binding: ActivityNavigationDrawerBinding

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.user_name -> Toast.makeText(applicationContext, "Clicked on user name", Toast.LENGTH_SHORT).show()

                R.id.profile -> Toast.makeText(applicationContext, "Clicked on profile", Toast.LENGTH_SHORT).show()

                R.id.shop -> Toast.makeText(applicationContext, "Clicked on shop", Toast.LENGTH_SHORT).show()

                R.id.my_order -> Toast.makeText(applicationContext, "Clicked on My order", Toast.LENGTH_SHORT).show()

                R.id.logout -> Toast.makeText(applicationContext, "Clicked on logout", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}