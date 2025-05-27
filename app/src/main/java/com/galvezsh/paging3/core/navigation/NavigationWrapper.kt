package com.galvezsh.paging3.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.galvezsh.paging3.presentation.detailScreen.DetailScreen
import com.galvezsh.paging3.presentation.mainScreen.MainScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost( navController = navController, startDestination = MainScreen ) {

        composable<MainScreen> {
            MainScreen()
        }

        composable<DetailScreen> {
            DetailScreen()
        }

    }
}