package com.example.proyecto_dsw9

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import android.util.Log

class PagoActivity : AppCompatActivity() {

    private lateinit var etNumeroTarjeta: EditText
    private lateinit var etNombreTitular: EditText
    private lateinit var etFechaExpiracion: EditText
    private lateinit var etCVV: EditText
    private lateinit var btnPagar: Button

    private val URL_COMPRA = "http://10.0.2.2/registrar_compra.php"
    private val usuarioId = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)

        etNumeroTarjeta = findViewById(R.id.editNumeroTarjeta)
        etNombreTitular = findViewById(R.id.etNombreTitular)
        etFechaExpiracion = findViewById(R.id.etFechaExpiracion)
        etCVV = findViewById(R.id.etCVV)
        btnPagar = findViewById(R.id.btnPagar)

        btnPagar.setOnClickListener {
            if (validarCampos()) {
                realizarCompra()
            }
        }
    }

    private fun validarCampos(): Boolean {
        if (etNumeroTarjeta.text.isBlank() ||
            etNombreTitular.text.isBlank() ||
            etFechaExpiracion.text.isBlank() ||
            etCVV.text.isBlank()
        ) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }
        if (etNumeroTarjeta.text.length != 16) {
            Toast.makeText(this, "Número de tarjeta inválido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (etCVV.text.length !in 3..4) {
            Toast.makeText(this, "CVV inválido", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun realizarCompra() {
        val productos = CarritoManager.obtenerProductos()
        if (productos.isEmpty()) {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            return
        }

        val total = productos.sumOf { it.precio }
        val numeroTarjeta = etNumeroTarjeta.text.toString()

        val queue = Volley.newRequestQueue(this)
        val request = object : StringRequest(
            Method.POST, URL_COMPRA,
            { response ->
                try {
                    val json = JSONObject(response)
                    val success = json.getBoolean("success")
                    val message = json.getString("message")

                    if (success) {
                        CarritoManager.limpiarCarrito()
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Error: $message", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    Log.e("ErrorEnServidor", "Error: ${e.message}", e)
                    Toast.makeText(this, "Respuesta inválida del servidor", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error en la compra: ${error.message}", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return hashMapOf(
                    "usuario_id" to usuarioId.toString(),
                    "total" to total.toString(),
                    "numero_tarjeta" to numeroTarjeta
                )
            }
        }

        queue.add(request)
    }
}
