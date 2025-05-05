package com.example.proyecto_dsw9

object CarritoManager {
    // Lista que almacena los productos en el carrito
    private val productosEnCarrito = mutableListOf<Producto>()

    // Método para agregar un producto al carrito
    fun agregarProducto(producto: Producto) {
        productosEnCarrito.add(producto)
    }

    // Método para obtener todos los productos en el carrito
    fun obtenerProductos(): List<Producto> {
        return productosEnCarrito
    }

    // Método para limpiar el carrito (eliminar todos los productos)
    fun limpiarCarrito() {
        productosEnCarrito.clear()
    }

    // Método para eliminar un producto específico del carrito
    fun eliminarProducto(producto: Producto) {
        productosEnCarrito.remove(producto)
    }
}
