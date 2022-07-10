package com.pk.photogallery

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        //requirePermissions()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // TODO: make it useful
        // https://developer.android.com/training/permissions/requesting
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

    private fun requirePermissions(): Boolean {
        val permissions = mutableListOf<String>()

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                ) {
                    // TODO: educational UI: explain why are the permission is required
                }
            }

            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                ) {
                    // TODO: educational UI: explain why are the permission is required
                }
            }

            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) -> {
                permissions.add(Manifest.permission.CAMERA)
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.CAMERA)
                ) {
                    // TODO: educational UI: explain why are the permission is required
                }
            }
        }

        requestPermissions(permissions.toTypedArray(), 0) // TODO: requestCode 0?

        return true
    }
}