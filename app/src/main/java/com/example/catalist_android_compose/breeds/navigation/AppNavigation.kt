package com.example.catalist_android_compose.breeds.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list",
    ) {
        composable(
            route = "list",
        ) {
            //list screen
        }

        composable(
            route = "details",
        ) {
            //details screen
        }
    }
}