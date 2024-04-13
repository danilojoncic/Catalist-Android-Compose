
import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.catalist_android_compose.breeds.core.compose.AppIconButton
import com.example.catalist_android_compose.breeds.core.compose.AssistChipExample
import com.example.catalist_android_compose.breeds.core.compose.SearchBar
import com.example.catalist_android_compose.breeds.domain.Cat
import com.example.catalist_android_compose.breeds.list.BreedsListState
import com.example.catalist_android_compose.breeds.list.BreedsListViewModel

@ExperimentalMaterial3Api
fun NavGraphBuilder.breedSearchScreen(
    route: String,
    navController: NavController,
)=composable(route = route) {
    //da bi funkcionisalo moram imati view model i state
    //trenutno state i viewmodel sam preuzeo iz liste
    val breedsListViewModel = viewModel<BreedsListViewModel>()
    val state by breedsListViewModel.state.collectAsState()

    BreedSearchScreen(
        state = state,
        onItemClick = {
            navController.navigate(route = "details/${it.id}")
        },
        onClose = {
            navController.navigateUp()
        }
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedSearchScreen(
    state: BreedsListState,
    onItemClick: (Cat) -> Unit,
    onClose: () -> Unit
) {
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Search results") },
                    navigationIcon = {
                        AppIconButton(
                            imageVector = Icons.Default.ArrowBack,
                            onClick = onClose,
                        )
                    },
                )
                Divider()
            }
        },
        content = {
        }
    )
}
