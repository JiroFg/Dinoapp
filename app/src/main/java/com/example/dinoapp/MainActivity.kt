package com.example.dinoapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.dinoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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

        preference = getSharedPreferences("MisPreferencias", MODE_PRIVATE)

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
}