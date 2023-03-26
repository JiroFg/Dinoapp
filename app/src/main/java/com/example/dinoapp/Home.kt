package com.example.dinoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.ConnectivityManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //SPLASH SCREEN
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // CONEXION A INTERNET
        val textoConexion = findViewById<TextView>(R.id.mensaje)

        Thread(Runnable {
            while (true){
                //AQUI SE DEBE PONER LO QUE DEBE ESTAR SI NO SE TIENE CONEXION A INTERNET

//                val dialog = error_conexion(
//                    onSubmitClickListener = {
//                        Toast.makeText(applicationContext,"Reintentando....", Toast.LENGTH_SHORT).show()
//                    }
//                )


                var textoInicial = "No tienes conexión"
                //Toast.makeText(applicationContext,"Conectado :)", Toast.LENGTH_SHORT).show()
                val conexion = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val leerInfo = conexion.allNetworkInfo
                for(x in leerInfo){
                    //NO IMPORTA SI ES CON DATOS O INTERNET, SOLO QUE ESTÉ CONECTADO
                    //AQUI SE DEBE PONER LO QUE VA A SUCEDER SI SE TIENE CONEXION A INTERNET
//                    if (x.typeName.equals("WIFI",ignoreCase = true))
//                        if (x.isConnected) textoInicial = "Conectado con WIFI"
//                    if (x.typeName.equals("MOBILE",ignoreCase = true))
//                        if (x.isConnected) textoInicial = "Conectado con DATOS"
                    if (x.typeName.equals("WIFI",ignoreCase = true) || (x.typeName.equals("MOBILE",ignoreCase = true)))
                        if (x.isConnected){
                            textoInicial = "Conectado con WIFI o con DATOS no importa"
                            //Toast.makeText(applicationContext,"Conectado :)", Toast.LENGTH_SHORT).show()
                        }
                }
                runOnUiThread{
                    textoConexion.text = textoInicial
                }
            }
        }).start()
    }
}