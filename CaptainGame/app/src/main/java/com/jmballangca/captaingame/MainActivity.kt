package com.jmballangca.captaingame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmballangca.captaingame.ui.theme.CaptainGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptainGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CaptainGame()
                }
            }
        }
    }
}

@Composable
fun CaptainGame() {
    val direction = remember {
        mutableStateOf("North")
    }

    val treasure = remember {
        mutableStateOf(0)
    }
    val stormOrTreasure = remember {
        mutableStateOf("")
    }
    Column(modifier =  Modifier.fillMaxSize() ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {

        Text(text = "Treasure Found: ${treasure.value}")
        Text(text = stormOrTreasure.value)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            direction.value = "North"
            val randomBool = Random.nextBoolean()

            if (randomBool) {
                treasure.value += 1
                stormOrTreasure.value = "Found A Treasure!!!"
            } else {
                stormOrTreasure.value = "Storm Ahead!!!"
            }
        }) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Head North")
        }

        Row {
            Button(onClick = {
                direction.value = "West"
                val randomBool = Random.nextBoolean()

                if (randomBool) {
                    treasure.value += 1
                    stormOrTreasure.value = "Found A Treasure!!!"
                } else {
                    stormOrTreasure.value = "Storm Ahead!!"
                }
            }) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Head West")
            }
            Text(text = direction.value, modifier = Modifier.padding(16.dp))
            Button(onClick = {
                direction.value = "East"
                val randomBool = Random.nextBoolean()

                if (randomBool) {
                    treasure.value += 1
                    stormOrTreasure.value = "Found A Treasure!!!"
                } else {
                    stormOrTreasure.value = "Storm Ahead!!!"
                }
            }) {
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Head East")
            }



        }

        Button(onClick = {
            direction.value = "South"
            val randomBool = Random.nextBoolean()

            if (randomBool) {
                treasure.value += 1
                stormOrTreasure.value = "Found A Treasure!!!"
            } else {
                stormOrTreasure.value = "Storm Ahead!!!"
            }
        }) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Head South")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CaptainGamePreview() {
    CaptainGameTheme {
        CaptainGame()
    }
}

