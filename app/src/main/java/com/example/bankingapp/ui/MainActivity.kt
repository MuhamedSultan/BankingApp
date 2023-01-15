package com.example.bankingapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bankingapp.R
import com.example.bankingapp.databinding.ActivityMainBinding
import com.example.bankingapp.viewmodel.ClientsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController=findNavController(R.id.NavHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        val appBarConfiguration= AppBarConfiguration(setOf(
            R.id.clientsragment,
            R.id.transferListFragment
        ))
        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {

        val navigate = findNavController(R.id.NavHostFragment)
        return navigate.navigateUp() || super.onSupportNavigateUp()
    }


}



