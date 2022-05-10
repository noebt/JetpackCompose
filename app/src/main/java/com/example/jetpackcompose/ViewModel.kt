package com.example.jetpackcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.api.ApiService
import com.example.jetpackcompose.model.MainCharacter
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var characterListResponse:List<MainCharacter> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getMovieList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val chatacterList = apiService.getCharacters()
                characterListResponse = chatacterList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun getMovieListByHouse(house: String) {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val chatacterList = apiService.getCharactersFromHouse(house = house)
                characterListResponse = chatacterList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}