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

        composable(
            route = "details/{dataId}",
            arguments = listOf(
                navArgument(name = "dataId"){
                    this.type = NavType.StringType
                    this.nullable = true
                }
            )
        ) {navBackStackEntry ->
            val dataId = navBackStackEntry.arguments?.getString("dataId")
            //ako smo nesto pogrijesili sa unosom parametara ili rute stavicemo praznu macku
            val data = if(dataId != null){
                BreedRepository.getById(id = dataId)
            }else{
                Cat(id = "maca",
                    weight = "0 lbs",
                    name = "Empty",
                    temperament = "Empty",
                    origin = "Empty",
                    description = "Empty.",
                    lifeSpan = "Empty",
                    indoor = 1,
                    lap = 1,
                    adaptability = 3,
                    affectionLevels = 4,
                    childFriendly = 1,
                    dogFriendly = 1,
                    energyLevel = 3,
                    grooming = 2,
                    healthIssues = 1,
                    inteligence = 3,
                    sheddingLevel = 2,
                    socialNeeds = 3,
                    strangerFriendly = 3,
                    vocalisation = 2,
                    experimental = 0,
                    hairless = 0,
                    natural = 1,
                    rare = 0,
                    rex = 0,
                    shortLegs = 0,
                    wikipediaLink = 0,
                    hypoallergenic = 0,
                    referenceImageId = "Empty",
                    link = "Empty",
                    numberOfLives = 1)
            }
            //sada se pitamo da li je macka koju smo povukli iz repozetorijuma dobra
            if (data != null){
                BreedDetailsScreen(
                    data = data,
                    onClose = {
                        navController.navigateUp()
                    }

                )
            } else{
                NoDataContent(id = dataId.toString())
            }
            //details screen
        }
    }

}

@Composable
private fun NoDataContent(
    id: String,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "There is no data for id '$id'.",
            fontSize = 18.sp,
        )
    }
}