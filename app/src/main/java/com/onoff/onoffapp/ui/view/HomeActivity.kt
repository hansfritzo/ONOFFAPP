package com.onoff.onoffapp.ui.view

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.onoff.onoffapp.BaseActivity
import com.onoff.onoffapp.R
import com.onoff.onoffapp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        barNavigationColorHome()

        binding.imgCustomers.setOnClickListener {

            val finalHost = NavHostFragment.create(R.navigation.nav_home)
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host, finalHost)
                .setPrimaryNavigationFragment(finalHost)
                .commit()

        }

        binding.imgAddCustomers.setOnClickListener {

            val finalHost = NavHostFragment.create(R.navigation.nav_add)
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host, finalHost)
                .setPrimaryNavigationFragment(finalHost)
                .commit()

        }
    }

    override fun onBackPressed() {

        val finalHost = NavHostFragment.create(R.navigation.nav_home)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host, finalHost)
            .setPrimaryNavigationFragment(finalHost)
            .commit()

    }
}