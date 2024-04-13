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
import com.example.catalist_android_compose.breeds.core.compose.CoilImage
import com.example.catalist_android_compose.breeds.core.compose.RareIndicator
import com.example.catalist_android_compose.breeds.core.compose.WikipediaButton
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
                    containerColor = topBarContainerColor,
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
            //da bi omogucio scrollovanje ovog ekrana koristim scrollState
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if(state.data?.name.equals("tigar")){
                    state.data?.let { CoilImage(url = "https://preview.redd.it/2h8agc9y3hx81.png?width=1080&crop=smart&auto=webp&s=527ddea80f673d5fc3c36b84ea91c2bec862e67c") }
                }else{
                    state.data?.let { CoilImage(url = it.url) }
                }
                Card (
                    Modifier.padding(horizontal = 30.dp)
                    .fillMaxWidth(),shape= CardDefaults.outlinedShape){
                    Text(text = "${state.data?.name}", fontSize = 32.sp)
                    Text(text = "Description: ${state.data?.description}", fontSize = 20.sp)
                    Text(text = "Countries of Origin: ${state.data?.origin}",fontSize = 20.sp)
                    Text(text = "Life Span: ${state.data?.lifeSpan}",fontSize = 20.sp)
                    Text(text = "Weight: ${state.data?.weight}",fontSize = 20.sp)
                    Text(text = "Temperament Properties:",fontSize = 20.sp)
                    if (state.data?.temperament?.isNotBlank() == true) { // Check if temperament is not empty
                        val temperamentList = state.data?.temperament?.split(", ") // Split temperament string into words
                        temperamentList?.forEach { temperament ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                AssistChipExample(title = temperament)
                            }
                        }
                    }
                    BreedAttributeScoring("Adaptability", state.data?.adaptability ?: 0)
                    BreedAttributeScoring("Affection Level", state.data?.affectionLevels ?: 0)
                    BreedAttributeScoring("Child Friendly", state.data?.childFriendly ?: 0)
                    BreedAttributeScoring("Dog Friendly", state.data?.dogFriendly ?: 0)
                    BreedAttributeScoring("Energy Levels", state.data?.energyLevel ?: 0)
                    BreedAttributeScoring("Energy Levels", state.data?.energyLevel ?: 0)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        RareIndicator(isRare = state.data?.rare == 1)
                    }
                    TextButton(
                        onClick = {  },
                        modifier = Modifier.padding(top = 16.dp),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            state.data?.wikipediaLink?.let { WikipediaButton(wikipediaLink = it) }
                        }
                    }
                }
            }
        },
    )
}
//
//@Composable
//fun openWikipediaLink(link: String) {
//    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
//    context.startActivity(intent)
//}


//composable(
//route = "details/{dataId}",
//arguments = listOf(
//navArgument(name = "dataId"){
//    this.type = NavType.StringType
//    this.nullable = true
//}
//)
//) {navBackStackEntry ->
//    val dataId = navBackStackEntry.arguments?.getString("dataId")
//    //ako smo nesto pogrijesili sa unosom parametara ili rute stavicemo praznu macku
//    val data = if(dataId != null){
//        BreedRepository.getById(id = dataId)
//    }else{
//        Cat(id = "maca",
//            weight = "0 lbs",
//            name = "Empty",
//            temperament = "Empty",
//            origin = "Empty",
//            description = "Empty.",
//            lifeSpan = "Empty",
//            indoor = 1,
//            lap = 1,
//            adaptability = 3,
//            affectionLevels = 4,
//            childFriendly = 1,
//            dogFriendly = 1,
//            energyLevel = 3,
//            grooming = 2,
//            healthIssues = 1,
//            inteligence = 3,
//            sheddingLevel = 2,
//            socialNeeds = 3,
//            strangerFriendly = 3,
//            vocalisation = 2,
//            experimental = 0,
//            hairless = 0,
//            natural = 1,
//            rare = 0,
//            rex = 0,
//            shortLegs = 0,
//            wikipediaLink = 0,
//            hypoallergenic = 0,
//            referenceImageId = "Empty",
//            link = "Empty",
//            numberOfLives = 1)
//    }
//    //sada se pitamo da li je macka koju smo povukli iz repozetorijuma dobra
//    if (data != null){
//        BreedDetailsScreen(
//            data = data,
//            onClose = {
//                navController.navigateUp()
//            }
//
//        )
//    } else{
//        NoDataContent(id = dataId.toString())
//    }
//    //details screen
//}

