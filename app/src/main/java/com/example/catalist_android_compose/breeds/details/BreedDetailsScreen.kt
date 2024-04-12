import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.catalist_android_compose.breeds.core.compose.AppIconButton
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
                Text(text = "ID: ${state.data?.name}")
                Text(text = "Weight: ${state.data?.weight}")
                Text(text = "Name: ${state.data?.name}")
                Text(text = "Temperament: ${state.data?.temperament}")
                Text(text = "Origin: ${state.data?.origin}")
                Text(text = "Description: ${state.data?.description}")
                Text(text = "Life Span: ${state.data?.lifeSpan}")
                Text(text = "Indoor: ${if (state.data?.indoor == 1) "Yes" else "No"}")
                Text(text = "Lap: ${if (state.data?.lap == 1) "Yes" else "No"}")
                Text(text = "Adaptability: ${state.data?.adaptability}")
                Text(text = "Affection Levels: ${state.data?.affectionLevels}")
                Text(text = "Child Friendly: ${if (state.data?.childFriendly == 1) "Yes" else "No"}")
                Text(text = "Dog Friendly: ${if (state.data?.dogFriendly == 1) "Yes" else "No"}")
                Text(text = "Energy Level: ${state.data?.energyLevel}")
                Text(text = "Grooming: ${state.data?.grooming}")
                Text(text = "Health Issues: ${state.data?.healthIssues}")
                Text(text = "Intelligence: ${state.data?.inteligence}")
                Text(text = "Shedding Level: ${state.data?.sheddingLevel}")
                Text(text = "Social Needs: ${state.data?.socialNeeds}")
                Text(text = "Stranger Friendly: ${state.data?.strangerFriendly}")
                Text(text = "Vocalisation: ${state.data?.vocalisation}")
                Text(text = "Experimental: ${if (state.data?.experimental == 1) "Yes" else "No"}")
                Text(text = "Hairless: ${if (state.data?.hairless == 1) "Yes" else "No"}")
                Text(text = "Natural: ${if (state.data?.natural == 1) "Yes" else "No"}")
                Text(text = "Rare: ${if (state.data?.rare == 1) "Yes" else "No"}")
                Text(text = "Rex: ${if (state.data?.rex == 1) "Yes" else "No"}")
                Text(text = "Short Legs: ${if (state.data?.shortLegs == 1) "Yes" else "No"}")
                Text(text = "Wikipedia Link: ${if (state.data?.wikipediaLink == 1) "Yes" else "No"}")
                Text(text = "Hypoallergenic: ${if (state.data?.hypoallergenic == 1) "Yes" else "No"}")
                Text(text = "Reference Image ID: ${state.data?.referenceImageId}")
                Text(text = "Link: ${state.data?.link}")
                Text(text = "Number of Lives: ${state.data?.numberOfLives}")
            }
        },
    )
}


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

