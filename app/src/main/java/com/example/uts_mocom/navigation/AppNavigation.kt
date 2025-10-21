package com.example.uts_mocom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uts_mocom.screen.AddEditContactScreen
import com.example.uts_mocom.screen.ListContactScreen
import com.example.uts_mocom.viewmodel.ContactViewModel

@Composable
fun ContactViewModel.AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListContactScreen(navController, this@AppNavigation)
        }
        composable("add") {
            AddEditContactScreen(navController, this@AppNavigation)
        }
        composable("edit/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
            if (index != null) {
                AddEditContactScreen(navController, this@AppNavigation, index)
            }
        }
    }
}
