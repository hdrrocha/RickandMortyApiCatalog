package com.example.rickandmortyapicatalog.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.rickandmortyapicatalog.R

class MainActivity : AppCompatActivity() {
    private lateinit var navHost: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupLayout()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = navHost.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupLayout() {
        navHost = supportFragmentManager.findFragmentById(R.id.fcvContent) as NavHostFragment
        val navController = navHost.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}