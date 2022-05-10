package com.example.jetpackcompose.model

import java.io.Serializable

data class MainCharacter(
    val id: String,
    val personaje: String,
    val apodo: String,
    val estudianteDeHogwarts: Boolean,
    val casaDeHogwarts: String,
    val interpretado_por: String,
    val hijos: List<String>,
    val imagen: String
) : Serializable