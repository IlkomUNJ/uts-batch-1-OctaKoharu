package com.example.uts_mocom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.uts_mocom.navigation.AppNavigation
import com.example.uts_mocom.viewmodel.ContactViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val contactViewModel: ContactViewModel = viewModel()

            contactViewModel.AppNavigation(navController)
        }
    }
}
