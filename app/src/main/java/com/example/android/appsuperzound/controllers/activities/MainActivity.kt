package com.example.android.appsuperzound.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.android.appsuperzound.R
import com.example.android.appsuperzound.controllers.fragments.FavouriteAlbumFragment
import com.example.android.appsuperzound.controllers.fragments.HomeFragment
import com.example.android.appsuperzound.controllers.fragments.ListAlbumFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
            item -> navigateTo(item)
    }

    private fun navigateTo(item: MenuItem) : Boolean{
        item.isChecked = true
        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.flFragment, getFragmentFor(item))
            .commit() > 0
    }

    private fun getFragmentFor(item: MenuItem): Fragment {
        return when (item.itemId){
            R.id.menu_home -> HomeFragment()
            R.id.menu_albums -> ListAlbumFragment()
            R.id.menu_favorite -> FavouriteAlbumFragment()
            else -> HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bnvMenu)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigateTo(navView.menu.findItem(R.id.menu_home))
    }
}