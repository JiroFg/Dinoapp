package com.example.dinoapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.dinoapp.databinding.ActivityAyudaBinding

class AyudaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAyudaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAyudaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonSalir.setOnClickListener {
            finish()
        }

    }
}