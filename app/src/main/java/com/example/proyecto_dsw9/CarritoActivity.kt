package com.example.proyecto_dsw9

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent

class CarritoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarritoAdapter
    private lateinit var totalTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        recyclerView = findViewById(R.id.recyclerViewCarrito)
        totalTextView = findViewById(R.id.textTotal)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener los productos del carrito
        val productosCarrito = CarritoManager.obtenerProductos()

        // Establecer el adaptador con el manejo de clics para eliminar productos
        adapter = CarritoAdapter(productosCarrito.toMutableList()) { producto ->
            eliminarProductoDelCarrito(producto)
        }
        recyclerView.adapter = adapter

        // Calcular y mostrar el total del carrito
        actualizarTotal()

        // Configuración del BottomNavigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = R.id.nav_carrito

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_carrito -> true // ya estamos aquí
                R.id.nav_perfil -> {
                    val intent = Intent(this, PerfilActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    // Método para eliminar un producto del carrito
    private fun eliminarProductoDelCarrito(producto: Producto) {
        // Eliminar el producto del carrito
        CarritoManager.eliminarProducto(producto)
        // Actualizar la lista del adaptador
        val productosCarrito = CarritoManager.obtenerProductos()
        adapter.actualizarLista(productosCarrito)
        // Actualizar el total del carrito
        actualizarTotal()
    }

    // Método para actualizar el total del carrito
    private fun actualizarTotal() {
        val productosCarrito = CarritoManager.obtenerProductos()
        val total = productosCarrito.sumOf { it.precio }
        totalTextView.text = "Total: $${"%.2f".format(total)}"
    }
}
