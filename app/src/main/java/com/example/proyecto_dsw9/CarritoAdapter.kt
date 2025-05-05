package com.example.proyecto_dsw9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarritoAdapter(
    private val productos: MutableList<Producto>,
    private val onItemClick: (Producto) -> Unit
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imageProducto)
        val nombre: TextView = itemView.findViewById(R.id.textNombreProducto)
        val eliminarBtn: ImageView = itemView.findViewById(R.id.imageEliminar)  // Botón eliminar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombre.text = producto.nombre
        // Usar Glide para cargar la imagen
        Glide.with(holder.itemView.context)
            .load(producto.imagenUrl)  // Asume que tienes una propiedad imagenUrl
            .into(holder.imagen)

        // Configurar el clic para eliminar el producto
        holder.eliminarBtn.setOnClickListener {
            onItemClick(producto)
        }
    }

    override fun getItemCount(): Int = productos.size

    // Método para actualizar la lista
    fun actualizarLista(nuevosProductos: List<Producto>) {
        productos.clear()
        productos.addAll(nuevosProductos)
        notifyDataSetChanged()
    }
}

