package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    val miServicio = ServicioSolicitudes()
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var regresar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.usernameLogin)
        password = findViewById(R.id.passwordLogin)
        login = findViewById(R.id.btnLogin)
        regresar = findViewById(R.id.btnRegresarRegistro)
        login.setOnClickListener() {
            val usuario = username.text.toString()
            val clave =  password.text.toString()
            if (usuario.isNullOrBlank() || clave.isNullOrBlank()){
                val toast = Toast.makeText(this, "Rellene los campos de Login", Toast.LENGTH_SHORT).show()
            }else{
                miServicio.loginUsuario(this, usuario, clave)
            }

        }

        regresar.setOnClickListener() {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}