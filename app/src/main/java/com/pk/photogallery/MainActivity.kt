package com.pk.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.FrameLayout
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.pk.photogallery.databinding.ActivityMainBinding
import com.pk.photogallery.ui.Home
import com.pk.photogallery.ui.MoreOptions
import com.pk.photogallery.ui.Profile
import com.pk.photogallery.ui.Search

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationBottom: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationBottom = binding.navigation
        navigationBottom.setOnItemSelectedListener(bottomNavListener)

        startSupportFragmentManager()
    }

    private fun startSupportFragmentManager(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.view_pager, Home())
            .commit()
    }

    private val bottomNavListener = NavigationBarView.OnItemSelectedListener {
        var fragment: Fragment? = null

        when(it.itemId){
            R.id.homeBtn -> fragment = Home()
            R.id.searchBtn -> fragment = Search()
            R.id.profileBtn -> fragment = Profile()
            R.id.hamburgerBtn -> fragment = MoreOptions()
        }

        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.view_pager, fragment).commit()
        }

        true
    }
}