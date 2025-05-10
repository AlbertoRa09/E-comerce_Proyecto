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
    private val onProductoEliminado: () -> Unit // Callback para actualizar el total
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imageProducto)
        val nombre: TextView = itemView.findViewById(R.id.textNombreProducto)
        val eliminarBtn: ImageView = itemView.findViewById(R.id.imageEliminar)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val producto = productos[position]
        holder.nombre.text = producto.nombre
        Glide.with(holder.itemView.context).load(producto.imagenUrl).into(holder.imagen)

        holder.eliminarBtn.setOnClickListener {
            CarritoManager.eliminarProducto(producto)
            productos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, productos.size)
            onProductoEliminado()
        }
    }


    override fun getItemCount(): Int = productos.size

    fun actualizarLista(nuevosProductos: List<Producto>) {
        productos.clear()
        productos.addAll(nuevosProductos)
        notifyDataSetChanged()
    }
}
