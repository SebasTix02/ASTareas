package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {
    val miServicio = ServicioSolicitudes()
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var repassword: EditText
    private lateinit var signup: Button
    private lateinit var signin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        repassword = findViewById(R.id.repassword)
        signup = findViewById(R.id.btnsignup)
        signin = findViewById(R.id.btnsignin)
        signup.setOnClickListener() {
            val usuario = username.text.toString()
            val clave =  password.text.toString()
            val reClave = repassword.text.toString()
            if (usuario.isNullOrBlank() || clave.isNullOrBlank() || reClave.isNullOrBlank()){
                val toast = Toast.makeText(this, "Rellene los campos de Registro", Toast.LENGTH_SHORT).show()
            }else{
                if (clave != reClave){
                    val toast = Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }else{
                    miServicio.registrarUsuario(this, usuario, clave)

                }
            }


        }
        signin.setOnClickListener() {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
        }
    }
}