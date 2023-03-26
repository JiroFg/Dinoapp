package com.example.dinoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.dinoapp.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var preference : SharedPreferences
    val prefShowIntro = "Intro"

    private val adapter by lazy { ViewPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)

        if(preference.getBoolean(prefShowIntro, true)) {

            val editor = preference.edit()
            editor.putBoolean(prefShowIntro, false)
            editor.commit()
            binding.pager.adapter = adapter

        }else {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
        // Conexion a internet
        //val textoConexion = findViewById<TextView>(R.id.mensaje)

        /*Thread(Runnable {
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
        }).start()*/
    }
}