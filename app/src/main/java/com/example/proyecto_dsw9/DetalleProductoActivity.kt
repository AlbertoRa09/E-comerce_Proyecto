package com.example.proyecto_dsw9

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetalleProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        // Recuperar el producto enviado desde la actividad anterior
        val producto = intent.getSerializableExtra("producto") as Producto

        // Referencias a las vistas
        val ivImagen: ImageView = findViewById(R.id.ivImagenDetalle)
        val tvNombre: TextView = findViewById(R.id.tvNombreDetalle)
        val tvDescripcion: TextView = findViewById(R.id.tvDescripcion)
        val tvPrecio: TextView = findViewById(R.id.tvPrecioDetalle)
        val btnAgregarCarrito: Button = findViewById(R.id.btnAgregarCarrito)
        val npCantidad: NumberPicker = findViewById(R.id.npCantidad)

        // Cargar la imagen del producto con Glide
        Glide.with(this).load(producto.imagenUrl).into(ivImagen)

        // Mostrar los datos del producto
        tvNombre.text = producto.nombre
        tvDescripcion.text = producto.descripcion
        tvPrecio.text = "Precio: B/. ${producto.precio}"

        // Configurar el NumberPicker para la cantidad
        npCantidad.minValue = 1
        npCantidad.maxValue = 10  // Esto puedes modificarlo si obtienes la cantidad real de la API
        npCantidad.value = 1

        // Lógica para el botón "Añadir al carrito"
        btnAgregarCarrito.setOnClickListener {
            val cantidadSeleccionada = npCantidad.value

            // Simulando agregar al carrito con un mensaje
            Toast.makeText(this, "${producto.nombre} x$cantidadSeleccionada añadido al carrito", Toast.LENGTH_SHORT).show()

            // Aquí puedes implementar el código para agregar la cantidad seleccionada al carrito
        }
    }
}
