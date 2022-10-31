package com.pk.photogallery

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar
import com.pk.photogallery.consts.PermissionRequest
import com.pk.photogallery.databinding.MainActivityBinding
import com.pk.photogallery.ui.Home
import com.pk.photogallery.ui.MoreOptions
import com.pk.photogallery.ui.Profile
import com.pk.photogallery.ui.Search

class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationBottom = binding.navigation
        navigationBottom.setOnItemSelectedListener(bottomNavListener)

        startSupportFragmentManager()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionRequest.READ_EXTERNAL_STORAGE -> {

            }

            PermissionRequest.ACCESS_FINE_LOCATION -> {

            }

            PermissionRequest.CAMERA -> {
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(binding.root, R.string.camera_permission_granted, Snackbar.LENGTH_SHORT).show()
                    startCamera()
                } else {
                    Snackbar.make(binding.root, R.string.camera_permission_denied, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    fun showCameraPreview() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED)
        {
            startCamera()
        }
        else
            requestCameraPermission()
    }

    private fun requestCameraPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            Snackbar.make(binding.root, R.string.camera_access_required, Snackbar.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PermissionRequest.CAMERA)
        } else {
            Snackbar.make(binding.root, R.string.camera_no_permmission, Snackbar.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PermissionRequest.CAMERA)
        }
    }

    private fun startCamera() {
//        val intent  = Intent(this, Camera)
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

    private lateinit var binding: MainActivityBinding
    private lateinit var navigationBottom: BottomNavigationView
}