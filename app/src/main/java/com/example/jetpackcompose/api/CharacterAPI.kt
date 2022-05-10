package com.example.jetpackcompose.api

import com.example.jetpackcompose.model.MainCharacter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(ApiConstants.END_POINTS)
    suspend fun getCharacters() : List<MainCharacter>

    @GET(ApiConstants.END_POINTS)
    suspend fun getCharactersFromHouse(@Query("casaDeHogwarts") house : String) : List<MainCharacter>

    companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}