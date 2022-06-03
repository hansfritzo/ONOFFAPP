package com.onoff.onoffapp.ui.view

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.onoff.onoffapp.BaseActivity
import com.onoff.onoffapp.R
import com.onoff.onoffapp.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        barNavigationColor()

    }

    override fun onBackPressed() {

        val finalHost = NavHostFragment.create(R.navigation.nav_splahs)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host, finalHost)
            .setPrimaryNavigationFragment(finalHost)
            .commit()

    }
}