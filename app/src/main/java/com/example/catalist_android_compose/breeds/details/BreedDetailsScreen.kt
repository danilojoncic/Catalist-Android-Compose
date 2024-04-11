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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.catalist_android_compose.breeds.core.compose.AppIconButton
import com.example.catalist_android_compose.breeds.domain.Cat
private val topBarContainerColor = Color.LightGray.copy(alpha = 0.5f)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailsScreen(
    data: Cat,
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
                    Text(text = "Selected Cat")
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
                Text(text = "ID: ${data.id}")
                Text(text = "Weight: ${data.weight}")
                Text(text = "Name: ${data.name}")
                Text(text = "Temperament: ${data.temperament}")
                Text(text = "Origin: ${data.origin}")
                Text(text = "Description: ${data.description}")
                Text(text = "Life Span: ${data.lifeSpan}")
                Text(text = "Indoor: ${if (data.indoor == 1) "Yes" else "No"}")
                Text(text = "Lap: ${if (data.lap == 1) "Yes" else "No"}")
                Text(text = "Adaptability: ${data.adaptability}")
                Text(text = "Affection Levels: ${data.affectionLevels}")
                Text(text = "Child Friendly: ${if (data.childFriendly == 1) "Yes" else "No"}")
                Text(text = "Dog Friendly: ${if (data.dogFriendly == 1) "Yes" else "No"}")
                Text(text = "Energy Level: ${data.energyLevel}")
                Text(text = "Grooming: ${data.grooming}")
                Text(text = "Health Issues: ${data.healthIssues}")
                Text(text = "Intelligence: ${data.inteligence}")
                Text(text = "Shedding Level: ${data.sheddingLevel}")
                Text(text = "Social Needs: ${data.socialNeeds}")
                Text(text = "Stranger Friendly: ${data.strangerFriendly}")
                Text(text = "Vocalisation: ${data.vocalisation}")
                Text(text = "Experimental: ${if (data.experimental == 1) "Yes" else "No"}")
                Text(text = "Hairless: ${if (data.hairless == 1) "Yes" else "No"}")
                Text(text = "Natural: ${if (data.natural == 1) "Yes" else "No"}")
                Text(text = "Rare: ${if (data.rare == 1) "Yes" else "No"}")
                Text(text = "Rex: ${if (data.rex == 1) "Yes" else "No"}")
                Text(text = "Short Legs: ${if (data.shortLegs == 1) "Yes" else "No"}")
                Text(text = "Wikipedia Link: ${if (data.wikipediaLink == 1) "Yes" else "No"}")
                Text(text = "Hypoallergenic: ${if (data.hypoallergenic == 1) "Yes" else "No"}")
                Text(text = "Reference Image ID: ${data.referenceImageId}")
                Text(text = "Link: ${data.link}")
                Text(text = "Number of Lives: ${data.numberOfLives}")
            }
        },
    )
}

