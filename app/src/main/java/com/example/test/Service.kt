package com.example.test

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class ServicioSolicitudes {
    fun registrarUsuario(contexto: Context, usuario: String, clave: String) {
        val url = "http://192.168.1.47:3001/api/user/register"
        val queue = Volley.newRequestQueue(contexto)

        val resultadoPost = object : StringRequest(
            Method.POST, url, Response.Listener<String> { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    val mensaje = jsonResponse.getString("msg")

                    // Mostrar el mensaje en el Toast
                    Toast.makeText(contexto, mensaje, Toast.LENGTH_LONG).show()
                    if (mensaje.startsWith("Usuario")) {
                        // Redirigir a otra vista
                        val intent = Intent(contexto, Login::class.java)
                        ContextCompat.startActivity(contexto, intent, null)
                    } else {
                        // Mostrar el mensaje en el Toast
                        Toast.makeText(contexto, mensaje, Toast.LENGTH_LONG).show()
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                println(error)
                Toast.makeText(contexto, "Usuario repetido", Toast.LENGTH_LONG).show()
            }) {

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                // Construye el objeto JSON que deseas enviar
                val jsonBody = JSONObject()
                jsonBody.put("usuario_usua", usuario)
                jsonBody.put("clave_usua", clave)

                // Convierte el objeto JSON en una cadena de bytes
                return jsonBody.toString().toByteArray(Charsets.UTF_8)
            }
        }

        queue.add(resultadoPost)
    }

    fun loginUsuario(contexto: Context, usuario: String, clave: String) {

        val url = "http://192.168.1.47:3001/api/user/login"
        val queue = Volley.newRequestQueue(contexto)

        val resultadoPost = object : StringRequest(
            Method.POST, url, Response.Listener<String> { response ->
                if (response.contains("Usuario Valido")) {
                    // Redirigir a otra vista
                    val intent = Intent(contexto, DashBoard::class.java)
                    ContextCompat.startActivity(contexto, intent, null)
                } else {
                    // Mostrar el mensaje en el Toast
                    Toast.makeText(contexto, response, Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                println(error)
                Toast.makeText(contexto, "Error $error", Toast.LENGTH_LONG).show()
            }) {

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                // Construye el objeto JSON que deseas enviar
                val jsonBody = JSONObject()
                jsonBody.put("usuario_usua", usuario)
                jsonBody.put("clave_usua", clave)

                // Convierte el objeto JSON en una cadena de bytes
                return jsonBody.toString().toByteArray(Charsets.UTF_8)
            }
        }

        queue.add(resultadoPost)
    }
}
