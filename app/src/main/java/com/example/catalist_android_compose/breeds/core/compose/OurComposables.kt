package com.example.catalist_android_compose.breeds.core.compose

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.catalist_android_compose.breeds.core.compose.theme.Purple80
import com.example.catalist_android_compose.breeds.domain.Cat

@Composable
fun AppIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint,
        )
    }
}

@Composable
fun AssistChipExample(title: String) {
    AssistChip(
        onClick = { Log.d("Assist chip", "hello world") },
        label = { Text(title) },
        leadingIcon = {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}

@Composable
fun CoilImage(url:String){
    Box(modifier = Modifier
        .height((500.dp))
        .width(500.dp),
            //.fillMaxHeight(),
        contentAlignment = Alignment.Center

    ){
        val painter = rememberImagePainter(data = url)
        Image(painter = painter, contentDescription = "opis",Modifier.fillMaxSize())
    }
}

@Composable
fun WikipediaButton(wikipediaLink: String) {
    val context = LocalContext.current
    val intent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse(wikipediaLink))
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { context.startActivity(intent) }) {
            Text(text = "Wikipedia")
        }
    }
}

@Composable
fun BreedAttributeScoring(attributeName: String, attributeValue: Int) {
    Column(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = attributeName, style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            repeat(attributeValue) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier.size(24.dp)
                )
            }
            repeat(5 - attributeValue) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(onClick: (String) -> Unit) {

    var searchText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // You can replace the TextField with your preferred search input widget
        // For demonstration, I'm using a basic TextField here
        TextField(
            modifier = Modifier.weight(1f),
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
        )

        IconButton(
            onClick = { onClick(searchText) }, // Trigger the search when the button is clicked
            modifier = Modifier.size(48.dp)
        ) {
            Icon(Icons.Default.Search, contentDescription = "Search")
        }
    }
}


@Composable
fun RareIndicator(isRare: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Is Rare: ",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(end = 8.dp)
        )
        if (isRare) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Rare",
                tint = Color.Green,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Not Rare",
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
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


@Composable
fun BreedDetailItem(title: String, detail: String) {
    Row {
        Text(text = title, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = detail, fontSize = 20.sp)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListItem(
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
        colors = CardDefaults.cardColors(Purple80) // Pinkish color
    )  {
        Text(
            modifier = Modifier.padding(all = 16.dp),
            text = data.name,
            fontSize = 32.sp
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
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .weight(weight = 1f),
                text = if (data.alternateName != null && data.alternateName.isNotBlank()) "Also known as: ${data.alternateName}" else "Also known as: Unknown",
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (data.temperament.isNotBlank()) { // Check if temperament is not empty
            val temperamentList =
                data.temperament.split(", ") // Split temperament string into words
            temperamentList.take(3).forEach { temperament ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AssistChipExample(title = temperament)
                }
            }
        }
    }
}

@Composable
fun BreedsList(
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


