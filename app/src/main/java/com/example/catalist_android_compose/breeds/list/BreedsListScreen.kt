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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.catalist_android_compose.breeds.core.compose.AssistChipExample
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
        )
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedsListScreen(
    state: BreedsListState,
    onItemClick: (Cat) -> Unit,

){
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(title = { Text(text = "A Cat List: Catalist") })
                Divider()
            }

        },
        content = {
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
    )
}

@Composable
private fun BreedsList(
    items:List<Cat>,
    paddingValues: PaddingValues,
    onItemClick: (Cat) -> Unit
){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        items.forEach {
            Column {
                key(it.name) {
                    CatListItem(
                        data = it,
                        onClick = {
                            onItemClick(it)
                        },
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}




@Composable
private fun CatListItem(
    data: Cat,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
    ) {
        Text(
            modifier = Modifier.padding(all = 16.dp),
            text = data.name
        )

        Row {
            val description_sentences = data.description.split(".")
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .weight(weight = 1f),
                text = description_sentences.get(0),
            )

            Icon(
                modifier = Modifier.padding(end = 16.dp),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
            )
        }
        Row {
            Text(modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .weight(weight = 1f),
                text = if (data.alternateName != null && data.alternateName.isNotBlank()) "Also known as: ${data.alternateName}" else "Also known as: Unknown",
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (data.temperament.isNotBlank()) { // Check if temperament is not empty
            Row {
                val temperamentList = data.temperament.split(", ") // Split temperament string into words
                temperamentList.forEach { temperament ->
                    AssistChipExample(title = temperament)
                }
            }
        }
    }
}