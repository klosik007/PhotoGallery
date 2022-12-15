package com.pk.photogallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayout
import com.pk.photogallery.R
import com.pk.photogallery.databinding.ProfileFragmentBinding

class Profile: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentBinding.inflate(layoutInflater)

        tabLayout = binding.tabHolder
        tabLayout.addOnTabSelectedListener(tabLayoutListener)

        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private val tabLayoutListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            when(tab?.tag){
                "PROFILE_TAB_PHOTOS" -> {

                }

                "PROFILE_TAB_FOLLOWERS" -> {

                }

                "PROFILE_TAB_FOLLOWING" -> {

                }

                "PROFILE_TAB_MOREINFO" -> {

                }
            }
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            // Handle tab reselect
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            // Handle tab unselect
        }
    }

    private lateinit var binding: ProfileFragmentBinding
    private lateinit var tabLayout: TabLayout
}