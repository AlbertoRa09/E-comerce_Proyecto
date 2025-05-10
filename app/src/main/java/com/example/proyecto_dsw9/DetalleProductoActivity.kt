package com.example.proyecto_dsw9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetalleProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        val producto = intent.getSerializableExtra("producto") as Producto

        // Referencias a las vistas
        val ivImagen: ImageView = findViewById(R.id.ivImagenDetalle)
        val tvNombre: TextView = findViewById(R.id.tvNombreDetalle)
        val tvDescripcion: TextView = findViewById(R.id.tvDescripcion)
        val tvPrecio: TextView = findViewById(R.id.tvPrecioDetalle)
        val btnAgregarCarrito: Button = findViewById(R.id.btnAgregarCarrito)
        val npCantidad: NumberPicker = findViewById(R.id.npCantidad)

        // Cargar la imagen del producto
        Glide.with(this).load(producto.imagenUrl).into(ivImagen)

        // Mostrar los datos del producto
        tvNombre.text = producto.nombre
        tvDescripcion.text = producto.descripcion
        tvPrecio.text = "Precio: B/. ${producto.precio}"


        npCantidad.minValue = 1
        npCantidad.maxValue = 10
        npCantidad.value = 1

        btnAgregarCarrito.setOnClickListener {
            val cantidadSeleccionada = npCantidad.value

            repeat(cantidadSeleccionada) {
                CarritoManager.agregarProducto(producto)
            }

            Toast.makeText(
                this,
                "${producto.nombre} x$cantidadSeleccionada añadido al carrito",
                Toast.LENGTH_SHORT
            ).show()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_carrito -> {
                    startActivity(Intent(this, CarritoActivity::class.java))
                    true
                }
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


    }
}
