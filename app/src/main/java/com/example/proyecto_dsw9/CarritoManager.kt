package com.example.proyecto_dsw9

object CarritoManager {
    private val productosEnCarrito = mutableListOf<Producto>()

    fun agregarProducto(producto: Producto) {
        productosEnCarrito.add(producto)
    }

    fun obtenerProductos(): List<Producto> {
        return productosEnCarrito
    }

    fun limpiarCarrito() {
        productosEnCarrito.clear()
    }
}
