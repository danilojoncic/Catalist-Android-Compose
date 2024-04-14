package com.example.catalist_android_compose.breeds.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.catalist_android_compose.breeds.core.compose.AssistChipExample
import com.example.catalist_android_compose.breeds.core.compose.BreedsList
import com.example.catalist_android_compose.breeds.core.compose.CatListItem
import com.example.catalist_android_compose.breeds.core.compose.SearchBar
import com.example.catalist_android_compose.breeds.core.compose.theme.Pink40
import com.example.catalist_android_compose.breeds.core.compose.theme.Purple80
import com.example.catalist_android_compose.breeds.domain.Cat

@ExperimentalMaterial3Api
fun NavGraphBuilder.breedsListScreen(
    route: String,
    navController: NavController,
)=composable(route = route) {
    val breedsListViewModel = viewModel<BreedsListViewModel>()
    val state by breedsListViewModel.state.collectAsState()

    BreedsListScreen(
            state = state,
            onItemClick = {
                navController.navigate(route = "details/${it.id}")
            },
            onSearch = { query ->
                breedsListViewModel.processUIEvent(SearchUIEvent.SeatchQueryChanged(query))
            }
        )
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedsListScreen(
    state: BreedsListState,
    onItemClick: (Cat) -> Unit,
    onSearch: (String) ->Unit

){
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    Purple80), title = { Text(text = "A Cat List: Catalist") })
                Divider()
                SearchBar(
                    onClick = onSearch
                )
            }

        },
        content = {
            if(state.filteredBreeds.isNotEmpty()){
                BreedsList(
                    paddingValues = it,
                    items = state.filteredBreeds,
                    onItemClick = onItemClick)
            }else{
                BreedsList(
                    paddingValues = it,
                    items = state.allBreedsFromState,
                    onItemClick = onItemClick,
                )
                if(state.allBreedsFromState.isEmpty()){
                    when (state.fetching) {
                        true -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        false -> {
                            if (state.error != null) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    val errorMessage = when (state.error) {
                                        is BreedsListState.ListError.ListUpdateFailed ->
                                            "Failed to load. Error message: ${state.error.cause?.message}."
                                    }
                                    Text(text = errorMessage)
                                }
                            } else {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(text = "No cat breeds.")
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
