package com.example.proyecto_dsw9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageButton


class ProductoAdapter(private val productos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imageProducto)
        val nombre: TextView = itemView.findViewById(R.id.textNombreProducto)
        val botonAgregar = itemView.findViewById<ImageButton>(R.id.btnAgregarCarrito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.imagen.setImageResource(producto.imagenRes)
        holder.nombre.text = producto.nombre

        holder.botonAgregar.setOnClickListener {
            CarritoManager.agregarProducto(producto)
            Toast.makeText(
                holder.itemView.context,
                "${producto.nombre} añadido al carrito",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = productos.size
}
