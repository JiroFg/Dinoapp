package com.example.dinoapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.dinoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), InterfaceTransferencia {

    private lateinit var binding: ActivityMainBinding

    lateinit var preference : SharedPreferences
    val prefShowIntro = "Intro"

    private val adapter by lazy { ViewPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {

        //SPLASH SCREEN
        installSplashScreen()

        super.onCreate(savedInstanceState)

        //VIEW DE BIENVENIDA
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
            finish()
        }

    }

    override fun transferirSexo(msg: Boolean) {
        Toast.makeText(this,msg.toString(), Toast.LENGTH_SHORT).show()
        Log.d("valorSexo: ",msg.toString())
    }

    override fun transferirNombre(nombre: String) {
        Toast.makeText(this,nombre, Toast.LENGTH_SHORT).show()
        Log.d("valorNombre: ",nombre)
    }

    override fun transferirImg(img: String) {

    }
}