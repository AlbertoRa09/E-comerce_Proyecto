package com.example.proyecto_dsw9

import java.io.Serializable

data class Producto(
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagenUrl: String
) : Serializable
