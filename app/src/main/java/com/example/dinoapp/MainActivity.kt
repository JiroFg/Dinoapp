package com.example.dinoapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Conexion a internet
        val textoConexion = findViewById<TextView>(R.id.mensaje)

        Thread(Runnable {
            while (true){
                //AQUI SE DEBE PONER LO QUE DEBE ESTAR SI NO SE TIENE CONEXION A INTERNET
                var textoInicial = "No tienes conexión"
                val conexion = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val leerInfo = conexion.allNetworkInfo
                for(x in leerInfo){
                    //AQUI SE DEBE PONER LO QUE VA A SUCEDER SI SE TIENE CONEXION A INTERNET
                    if (x.typeName.equals("WIFI",ignoreCase = true))
                        if (x.isConnected) textoInicial = "Conectado con WIFI"
                    if (x.typeName.equals("MOBILE",ignoreCase = true))
                        if (x.isConnected) textoInicial = "Conectado con DATOS"
                }
                runOnUiThread{
                    textoConexion.text = textoInicial
                }
            }
        }).start()
    }
}