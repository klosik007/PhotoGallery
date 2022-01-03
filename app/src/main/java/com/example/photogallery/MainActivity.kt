package com.example.photogallery
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tab: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tab = findViewById(R.id.tabHolder)

        supportFragmentManager
            .beginTransaction()
            //.add(R.id.fragment_container, FreakyLauncherFragment())
            .commit()
    }
}