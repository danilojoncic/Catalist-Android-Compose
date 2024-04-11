package com.example.catalist_android_compose.breeds.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catalist_android_compose.breeds.compose.theme.CatalistAndroidComposeTheme
import com.example.catalist_android_compose.breeds.model.Cat
import com.example.catalist_android_compose.breeds.repository.SampleData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedsListScreen(
    items: List<Cat>,
    onItemClick: (Cat) -> Unit,

){
    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(title = { Text(text = "All cat breeds:") })
                Divider()
            }

        },
        content = {
            // LazyColumn should be used for infinite lists which we will
            // learn soon. In the meantime we can use Column with verticalScroll
            // modifier so it can be scrollable.
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(it),
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
    )

}

@Composable
private fun CatListItem(
    data: Cat,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
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
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .weight(weight = 1f),
                text = data.description,
            )

            Icon(
                modifier = Modifier.padding(end = 16.dp),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
            )
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewPasswordListScreen() {
    CatalistAndroidComposeTheme {
        BreedsListScreen(
            items = SampleData,
            onItemClick = {}
        )
    }
}