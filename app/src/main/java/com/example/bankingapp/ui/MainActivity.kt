package com.example.bankingapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bankingapp.R
import com.example.bankingapp.models.Clients
import com.example.bankingapp.viewmodel.ClientsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ClientsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))

       // bottomNavigationView.setupWithNavController(fragment.findNavController())



        viewModel = ViewModelProvider(this)[ClientsViewModel::class.java]
       isAppFirstTimeRun()
    }

    override fun onSupportNavigateUp(): Boolean {

        val navigate = findNavController(R.id.fragment)
        return navigate.navigateUp() || super.onSupportNavigateUp()
    }


   private fun isAppFirstTimeRun() {
        val shared = getSharedPreferences("isFirstTime", MODE_PRIVATE)
        if (shared.getBoolean("check", true)) {
            viewModel.addClient(
                listOf(
                    Clients(
                        "Muhamed", "Muhamed@gmail.com", "50000",
                        "01023459672", "123654"
                    ), Clients(
                        "Mahmoud", "Mahmoud@gmail.com", "70000",
                        "01223959175", "143844"
                    ), Clients(
                        "Khaled", "Khaled@gmail.com", "30000",
                        "01286959815", "141854"
                    ), Clients(
                        "Ali", "Ali@gmail.com", "100000",
                        "01185929810", "543850"
                    ), Clients(
                        "Sameh", "Sameh@gmail.com", "800000",
                        "01585917830", "243731"
                    )
                )
            )
            val editor = shared.edit()
            editor.putBoolean("check", false)
            editor.apply()
        }

    }
}


