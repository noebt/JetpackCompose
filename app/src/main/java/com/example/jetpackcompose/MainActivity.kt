package com.example.jetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.jetpackcompose.model.MainCharacter


class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()
    var selectedHouse : String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.getMovieList()
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                HomeContent(characterList = mainViewModel.characterListResponse)
            }
        }
    }

    @Composable
    fun CharacterItem(character: MainCharacter, index: Int, selectedIndex: Int, onClick: (Int, MainCharacter) -> Unit) {
        val backgroundColor =
            if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background
        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth()
                .clickable { onClick(index, character) }
                .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
        ) {
            Surface(color = backgroundColor) {

                Row(
                    Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                ) {

                    Image(
                        painter = rememberImagePainter(
                            data = character.imagen,

                            builder = {
                                scale(coil.size.Scale.FILL)
                                placeholder(R.drawable.placeholder)
                                transformations(CircleCropTransformation())

                            }
                        ),
                        contentDescription = "Character Image",
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.2f)
                    )


                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxHeight()
                            .weight(0.8f)
                    ) {
                        Text(
                            text = character.personaje,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = character.casaDeHogwarts,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier
                                .background(
                                    Color.LightGray
                                )
                                .padding(4.dp)
                        )
                    }
                }
            }
        }

    }

    @Composable
    fun HomeContent(characterList: List<MainCharacter>) {
        var selectedIndex by remember { mutableStateOf(-1) }
        Column() {
            HouseDropdown()
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp, 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button(onClick = {
                    mainViewModel.getMovieListByHouse(selectedHouse)
                }) {
                    Text("Search")
                }
                Button(onClick = {
                    mainViewModel.getMovieList();
                }) {
                    Text("Clear")
                }
            }
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 8.dp)
            ) {

                itemsIndexed(items = characterList) { index, item ->
                    CharacterItem(character = item, index, selectedIndex) { i, selectedCaracter ->
                        selectedIndex = i
                        val intent = Intent(this@MainActivity,CharacterActivity::class.java)
                        intent.putExtra("Character",item)
                        startActivity(intent)
                    }
                }
            }
        }

    }

    @Composable
    fun HouseDropdown(){

        // Declaring a boolean value to store
        // the expanded state of the Text Field
        var mExpanded by remember { mutableStateOf(false) }

        // Create a list of cities
        val mCities = listOf("Gryffindor", "Slytherin", "Ravenclaw")

        // Create a string value to store the selected city
        var mSelectedText by remember { mutableStateOf("") }

        var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

        // Up Icon when expanded and down icon when collapsed
        val icon = if (mExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Column(Modifier.padding(20.dp)) {

            // Create an Outlined Text Field
            // with icon and not expanded
            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = {Text("Label")},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                }
            )

            // Create a drop-down menu with list of cities,
            // when clicked, set the Text Field text as the city selected
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
            ) {
                mCities.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label
                        mExpanded = false
                        selectedHouse = mSelectedText
                    }) {
                        Text(text = label)
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true, name = "Vista app")
//@Composable
//fun helloApp(){
//    JetpackComposeTheme {
//        // A surface container using the 'background' color from the theme
//        Surface(color = MaterialTheme.colors.background) {
//            Greeting("Noelia")
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!", modifier = Modifier.padding(16.dp))
//}
//
//@Preview(showBackground = true, name = "Vista login")
//@Composable
//fun DefaultPreview() {
//    JetpackComposeTheme {
//        Greeting("Noelia")
//    }
//}