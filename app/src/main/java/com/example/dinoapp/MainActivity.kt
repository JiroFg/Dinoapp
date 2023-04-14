package com.example.dinoapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dinoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), InterfaceTransferencia {

    private lateinit var binding: ActivityMainBinding
    private var sexo: Boolean = false
    private var nombre: String = ""
    private var img: Int = 1

    lateinit var preference : SharedPreferences
    val prefShowIntro = "Intro"

    private val adapter by lazy { ViewPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //VIEW DE BIENVENIDA
        preference = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        //si las preferencias estan por defecto "true" redirige al adapter
        if(preference.getBoolean(prefShowIntro, true)) {
            binding.pager.adapter = adapter
        }else{
            //caso contrario nos redirige a la pantalla home
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun transferirSexo(sexo: Boolean) {
        Log.d("valorSexo: ",sexo.toString())
        this.sexo = sexo
    }

    override fun transferirNombre(nombre: String) {
        Log.d("valorNombre: ",nombre)
        this.nombre = nombre
    }

    override fun transferirImg(img: Int) {
        //Log.d("valorImg: ",img.toString())
        this.img = img
        //modificación de preferencias al finalizar la configuración del usuario
        val editor = preference.edit()
        //cambia a false para que no se vuelva a mostrar la configuración inicial
        editor.putBoolean(prefShowIntro, false)
        editor.apply()
        //coloca los datos en el intent y redirige a la pantalla home
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}