package com.example.proyecto_dsw9
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerViewProductos)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Lista de productos
        val productos = listOf(
            Producto("Laptop Gamer", R.drawable.laptop),
            Producto("Auriculares", R.drawable.audifonos),
            Producto("Mouse RGB", R.drawable.mouse),
            Producto("Monitor 4K", R.drawable.monitor)
        )

        // Crear y asignar el adaptador
        adapter = ProductoAdapter(productos)
        recyclerView.adapter = adapter

//        val searchView = findViewById<SearchView>(R.id.searchView)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // Aquí puedes filtrar productos
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Filtrado en tiempo real (si quieres)
//                return true
//            }
//        })
//
//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        bottomNav.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_home -> {
//                    // Ya estás en Home
//                    true
//                }
//                R.id.nav_carrito -> {
//                    val intent = Intent(this, CarritoActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.nav_perfil -> {
//                    val intent = Intent(this, PerfilActivity::class.java)
//                    startActivity(intent)
//                    true
//                }
//                else -> false
//            }
//        }


    }
}

