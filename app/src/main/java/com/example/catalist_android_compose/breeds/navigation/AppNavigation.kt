@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.catalist_android_compose.breeds.navigation

import BreedDetailsScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import breedDetailsScreen
import breedSearchScreen
import com.example.catalist_android_compose.breeds.list.BreedsListScreen
import com.example.catalist_android_compose.breeds.domain.Cat
import com.example.catalist_android_compose.breeds.list.breedsListScreen
import com.example.catalist_android_compose.breeds.repository.BreedRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list",
    ) {

        breedsListScreen(
            route = "list",
            navController = navController
        )
        breedDetailsScreen(
            route = "details/{dataId}",
            arguments = listOf(
                navArgument(name = "dataId") {
                    this.type = NavType.StringType
                    this.nullable = true
                }
            ),
            navController = navController
        )
        breedSearchScreen(
            route = "search",
            navController = navController
        )
    }
}
