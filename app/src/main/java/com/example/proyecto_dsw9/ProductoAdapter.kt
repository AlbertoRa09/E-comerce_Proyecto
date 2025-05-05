package com.example.proyecto_dsw9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductoAdapter(
    private val productos: MutableList<Producto>,
    private val onItemClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    // ViewHolder para cada item
    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textNombreProducto)
        val precio: TextView = itemView.findViewById(R.id.textPrecioProducto)
        val imagen: ImageView = itemView.findViewById(R.id.imageProducto)
    }

    // Crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    // Vincular los datos del producto a las vistas
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]

        // Asignar el nombre y precio del producto
        holder.nombre.text = producto.nombre
        holder.precio.text = "B/. ${producto.precio}"

        // Cargar la imagen usando Glide
        Glide.with(holder.itemView.context)
            .load(producto.imagenUrl)
            .error(R.drawable.laptop)
            .into(holder.imagen)

        // Configurar el clic en el item para abrir detalles del producto
        holder.itemView.setOnClickListener { onItemClick(producto) }
    }

    // Devolver el tamaño de la lista de productos
    override fun getItemCount(): Int = productos.size

    // Actualizar la lista de productos en el adaptador
    fun actualizarLista(nuevosProductos: List<Producto>) {
        productos.clear()
        productos.addAll(nuevosProductos)
        notifyDataSetChanged()
    }
}
