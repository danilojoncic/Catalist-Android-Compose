import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.catalist_android_compose.breeds.core.compose.AppIconButton
import com.example.catalist_android_compose.breeds.core.compose.AssistChipExample
import com.example.catalist_android_compose.breeds.core.compose.BreedAttributeScoring
import com.example.catalist_android_compose.breeds.core.compose.BreedDetailItem
import com.example.catalist_android_compose.breeds.core.compose.CoilImage
import com.example.catalist_android_compose.breeds.core.compose.RareIndicator
import com.example.catalist_android_compose.breeds.core.compose.WikipediaButton
import com.example.catalist_android_compose.breeds.core.compose.theme.Purple80
import com.example.catalist_android_compose.breeds.details.BreedDetailsState
import com.example.catalist_android_compose.breeds.details.BreedsDetailsViewModel

private val topBarContainerColor = Color.LightGray.copy(alpha = 0.5f)



fun NavGraphBuilder.breedDetailsScreen(
    route: String,
    arguments: List<NamedNavArgument>,
    navController: NavController
) = composable(route = route,
) { navBackStackEntry ->
    val dataId = navBackStackEntry.arguments?.getString("dataId")
        ?: throw IllegalArgumentException("id is required.")
    val breedsDetailViewModel = viewModel<BreedsDetailsViewModel>(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BreedsDetailsViewModel(breedId = dataId) as T
            }
        },
    )
    val state by breedsDetailViewModel.state.collectAsState()

    BreedDetailsScreen(
        state = state,
        onClose = {
            navController.navigateUp()
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailsScreen(
    state: BreedDetailsState,
    onClose: () -> Unit
) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    Purple80,
                    scrolledContainerColor = topBarContainerColor,
                ),
                navigationIcon = {
                    AppIconButton(
                        imageVector = Icons.Default.ArrowBack,
                        onClick = onClose,
                    )
                },
                title = {
                    Text(text = "${state.data?.name}")
                }
            )
        },
        content = { paddingValues ->
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(Modifier.height(200.dp)) {
                    state.data?.let { CoilImage(url = it.url) }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    CardDefaults.elevatedShape,
                    CardDefaults.cardColors(Purple80)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Description:", fontSize = 24.sp)
                        Text(text = state.data?.description ?: "", fontSize = 16.sp)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    CardDefaults.elevatedShape,
                    CardDefaults.cardColors(Purple80)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Details:", fontSize = 24.sp)
                        BreedDetailItem("Countries of Origin:", state.data?.origin ?: "")
                        BreedDetailItem("Life Span:", state.data?.lifeSpan ?: "")
                        BreedDetailItem("Weight:", state.data?.weight ?: "")
                        if (state.data?.temperament?.isNotBlank() == true) {
                            val temperamentList = state.data.temperament.split(", ")
                            temperamentList.forEach { temperament ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    AssistChipExample(title = temperament)
                                }
                            }
                        }                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    CardDefaults.elevatedShape,
                    CardDefaults.cardColors(Purple80)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Text(text = "Attributes:", fontSize = 24.sp)
                        BreedAttributeScoring("Adaptability", state.data?.adaptability ?: 0)
                        BreedAttributeScoring("Affection Level", state.data?.affectionLevels ?: 0)
                        BreedAttributeScoring("Child Friendly", state.data?.childFriendly ?: 0)
                        BreedAttributeScoring("Dog Friendly", state.data?.dogFriendly ?: 0)
                        BreedAttributeScoring("Energy Levels", state.data?.energyLevel ?: 0)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            RareIndicator(isRare = state.data?.rare == 1)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    CardDefaults.elevatedShape,
                    CardDefaults.cardColors(Purple80)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        TextButton(
                            onClick = {  },
                            modifier = Modifier.padding(top = 8.dp),
                        ) {
                            WikipediaButton(wikipediaLink = state.data?.wikipediaLink ?: "")
                        }
                    }
                }
            }
        }
    )
}

