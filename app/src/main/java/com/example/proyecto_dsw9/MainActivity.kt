package com.example.proyecto_dsw9

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var passwordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navegar a la pantalla de home
        val textViewSkibidi = findViewById<TextView>(R.id.textView11)

        textViewSkibidi.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


        // Navegar a la pantalla de registro

        val textViewRegistro = findViewById<TextView>(R.id.registro)
        textViewRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        // Mostrar/ocultar contraseña
        val editTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val imageViewToggle = findViewById<ImageView>(R.id.imageViewToggle)

        imageViewToggle.setOnClickListener {
            passwordVisible = !passwordVisible

            if (passwordVisible) {
                editTextPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                imageViewToggle.setImageResource(R.drawable.abierto)
            } else {
                editTextPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                imageViewToggle.setImageResource(R.drawable.cerrado)
            }

            editTextPassword.setSelection(editTextPassword.text.length)
        }


    }
}
