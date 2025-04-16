package com.example.proyecto_dsw9

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {
    private var passwordVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Mostrar/ocultar contraseña Login
        val editTextPassword1 = findViewById<EditText>(R.id.editTextTextPassword1)
        val imageViewToggle1 = findViewById<ImageView>(R.id.imageViewToggle1)

        imageViewToggle1.setOnClickListener {
            passwordVisible = !passwordVisible

            if (passwordVisible) {
                editTextPassword1.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                imageViewToggle1.setImageResource(R.drawable.abierto)
            } else {
                editTextPassword1.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                imageViewToggle1.setImageResource(R.drawable.cerrado)
            }

            editTextPassword1.setSelection(editTextPassword1.text.length)
        }

        // Navegar a la pantalla de login

        val textViewLogin = findViewById<TextView>(R.id.login)
        textViewLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}