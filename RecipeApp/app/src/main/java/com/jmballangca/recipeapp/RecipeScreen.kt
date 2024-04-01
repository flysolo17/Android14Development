package com.jmballangca.recipeapp

import android.widget.ImageView.ScaleType
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.size.Scale
import com.jmballangca.recipeapp.models.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen() {
    val recipeViewModel = RecipeViewModel()
    val uiState by remember {
        recipeViewModel.recipeState
    }
    val appBarColors = topAppBarColors(
        navigationIconContentColor = MaterialTheme.colorScheme.background,
        actionIconContentColor = MaterialTheme.colorScheme.background,
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.background
    )
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "JM Recipes")},  colors = appBarColors) }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            when {
                uiState.loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                uiState.error != null -> {
                    Text(text = uiState.error.toString())
                } else -> {
                    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                    items(uiState.recipes, key = {it.recipeId}) {
                        RecipeCard(recipe = it, onRecipeClicked = {
                            selectedRecipe = it
                        })
                        }
                    }
                }
            }
        }
        selectedRecipe?.let {
            ViewRecipe(recipe = it, onDismiss = { selectedRecipe = null }, appBarColors = appBarColors)
        }

    }

  
}
@Composable
fun RecipeCard(recipe: Recipe ,onRecipeClicked : () -> Unit) {
    val customCardColors = cardColors(
        containerColor = Color.White
    )
    Card(modifier = Modifier
        .clickable(onClick = onRecipeClicked)
        .fillMaxWidth()
        .height(250.dp)
        .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = customCardColors) {
        SubcomposeAsyncImage(
            model = recipe.imageUrl,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            error={
                painterResource(R.drawable.food)
            },
            contentDescription ="food image",
        )
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewRecipe(recipe: Recipe ,onDismiss : () -> Unit ,appBarColors: TopAppBarColors) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
           usePlatformDefaultWidth = false
        )
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                topBar = { TopAppBar(
                    title = { Text(text = recipe.name)},
                    navigationIcon = {
                        IconButton(onClick = onDismiss) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },colors = appBarColors) }
            ) {

                Column(modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())) {
                    SubcomposeAsyncImage(
                        model = recipe.imageUrl,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                        error={
                            painterResource(R.drawable.food)
                        },
                        contentDescription ="food image",
                    )
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween , modifier = Modifier.fillMaxWidth()) {
                            Column() {
                                Text(text = "Cook Time",   style = MaterialTheme.typography.titleSmall,)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = recipe.cookTime , style = MaterialTheme.typography.bodyMedium)
                            }
                            Column() {
                                Text(text = "Making Amount",   style = MaterialTheme.typography.titleSmall,)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = recipe.makingAmount , style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Description",   style = MaterialTheme.typography.titleMedium,)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = recipe.description , style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Ingredients",   style = MaterialTheme.typography.titleMedium,)
                        Text(text = recipe.ingredients.joinToString(separator = "\n"), style = MaterialTheme.typography.bodyMedium)

                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Tips",   style = MaterialTheme.typography.titleMedium,)
                        Text(text = recipe.tips.joinToString(separator = "\n"), style = MaterialTheme.typography.bodyMedium)

                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Additional 1",   style = MaterialTheme.typography.titleMedium,)
                        Text(text = recipe.additions1.joinToString(separator = "\n"), style = MaterialTheme.typography.bodyMedium)

                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Additional 2",   style = MaterialTheme.typography.titleMedium,)
                        Text(text = recipe.additions2.joinToString(separator = "\n"), style = MaterialTheme.typography.bodyMedium)

                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Additional 3",   style = MaterialTheme.typography.titleMedium,)
                        Text(text = recipe.additions3.joinToString(separator = "\n"), style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

    }
}