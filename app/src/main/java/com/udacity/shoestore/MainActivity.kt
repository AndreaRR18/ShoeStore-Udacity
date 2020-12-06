package com.udacity.shoestore

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment

        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(binding.toolbar)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.appbarLayout.visibility = (
                    when (destination.id) {
                        R.id.loginFragment -> View.GONE
                        R.id.welcomeFragment -> View.GONE
                        R.id.instructionFragment -> View.GONE
                        else -> View.VISIBLE
                    })
        }

        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)

        Timber.plant(Timber.DebugTree())
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

}

