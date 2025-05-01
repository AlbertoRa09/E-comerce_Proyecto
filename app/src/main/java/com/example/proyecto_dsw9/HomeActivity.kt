package com.example.proyecto_dsw9

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject
import android.util.Log


class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerViewProductos)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter = ProductoAdapter(mutableListOf()) { producto ->
            // Manejar el click en un producto
            val intent = Intent(this, DetalleProductoActivity::class.java)
            intent.putExtra("producto", producto)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        cargarProductosDesdeWeb()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_carrito -> {
                    startActivity(Intent(this, CarritoActivity::class.java))
                    true
                }
                R.id.nav_perfil -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun cargarProductosDesdeWeb() {
        val url = "http://10.0.2.2/get_productos.php"

        val queue = Volley.newRequestQueue(this)
        val request = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            val listaProductos = mutableListOf<Producto>()

            for (i in 0 until response.length()) {
                val productoJson: JSONObject = response.getJSONObject(i)
                val nombre = productoJson.getString("name")
                val descripcion = productoJson.getString("description")
                val precio = productoJson.getDouble("price")
                val imagenUrl = productoJson.getString("img")

                // Crear el objeto Producto
                val producto = Producto(nombre, descripcion, precio, imagenUrl)

                // Agregar a la lista
                listaProductos.add(producto)
            }

            // Actualizar la lista en el adaptadors
            adapter.actualizarLista(listaProductos)
        }, { error ->
            Toast.makeText(this, "Error al cargar productos: ${error.message}", Toast.LENGTH_SHORT).show()
        })

        queue.add(request)
    }
}
