package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.jetpackcompose.model.MainCharacter
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class CharacterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val receivedObject: MainCharacter =
                        intent?.getSerializableExtra("Character") as MainCharacter
                    Greeting(character = receivedObject)
                }
            }
        }
    }

    @Composable
    fun Greeting(character: MainCharacter) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(
                    data = character.imagen,

                    builder = {
                        scale(Scale.FILL)
                        placeholder(R.drawable.placeholder)
                    }
                ),
                contentDescription = "Character Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(350.dp,250.dp)
            )

            Row (
                modifier = Modifier.padding(16.dp)
                    ){
                Text(
                    text = "Nombre: ",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = character.personaje,
                    style = MaterialTheme.typography.subtitle1,
                )
            }

            Row (
                modifier = Modifier.padding(16.dp)
            ){
                Text(
                    text = "Apodo: ",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = character.apodo,
                    style = MaterialTheme.typography.subtitle1,
                )
            }

            Row (
                modifier = Modifier.padding(16.dp)
            ){
                Text(
                    text = "Interpretado por: ",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = character.interpretado_por,
                    style = MaterialTheme.typography.subtitle1,
                )
            }

            Row (
                modifier = Modifier.padding(16.dp)
            ){
                Text(
                    text = "Casa de Hogwarts: ",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = character.casaDeHogwarts,
                    style = MaterialTheme.typography.subtitle1,
                )
            }

        }
    }
}