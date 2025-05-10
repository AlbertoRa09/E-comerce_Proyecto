package com.example.proyecto_dsw9

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject

class CarritoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarritoAdapter
    private lateinit var totalTextView: TextView
    private lateinit var btnRealizarCompra: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        recyclerView = findViewById(R.id.recyclerViewCarrito)
        totalTextView = findViewById(R.id.textTotal)
        btnRealizarCompra = findViewById(R.id.btnRealizarCompra)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CarritoAdapter(CarritoManager.obtenerProductos().toMutableList()) {
            actualizarTotal()
        }


        recyclerView.adapter = adapter
        actualizarTotal()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = R.id.nav_carrito
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_carrito -> true
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_perfil -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }

        btnRealizarCompra.setOnClickListener {
            val total = calcularTotal()

            if (total > 0.0) {
                val intent = Intent(this, PagoActivity::class.java)
                intent.putExtra("TOTAL", total)
                intent.putExtra("USUARIO_ID", 5) // ID fijo por ahora
                startActivity(intent)
            } else {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun eliminarProductoDelCarrito(producto: Producto) {
        CarritoManager.eliminarProducto(producto)
        val productosCarrito = CarritoManager.obtenerProductos()
        adapter.actualizarLista(productosCarrito)
        actualizarTotal()
    }

    private fun actualizarTotal() {
        val total = calcularTotal()
        totalTextView.text = "Total: $${"%.2f".format(total)}"
    }

    private fun calcularTotal(): Double {
        val productosCarrito = CarritoManager.obtenerProductos()
        return productosCarrito.sumOf { it.precio }
    }

    private fun realizarCompra(usuarioId: Long, total: Double, context: Context) {
        val url = "http://10.0.2.2/registrar_compra.php"
        val queue = Volley.newRequestQueue(context)

        val jsonBody = JSONObject().apply {
            put("usuario_id", usuarioId)
            put("total", total)
        }

        val request = object : JsonObjectRequest(
            Method.POST, url, jsonBody,
            { response ->
                val success = response.getBoolean("success")
                val message = response.getString("message")
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                if (success) {
                    CarritoManager.limpiarCarrito()
                    adapter.actualizarLista(emptyList())
                    actualizarTotal()
                }
            },
            { error ->
                Toast.makeText(context, "Error al realizar la compra", Toast.LENGTH_SHORT).show()
            }
        ) {}

        queue.add(request)
    }
}
