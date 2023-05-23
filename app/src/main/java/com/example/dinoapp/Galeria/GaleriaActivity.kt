package com.example.dinoapp.Galeria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ActivityGaleriaBinding

class GaleriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGaleriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGaleriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val thumbnailRecyclerView: RecyclerView = findViewById(R.id.thumbnailRecyclerView)

// Configurar el ViewPager2
        val adapter = GalleryPagerAdapter()
        viewPager.adapter = adapter

// Configurar el RecyclerView de las miniaturas
        val thumbnailAdapter = ThumbnailAdapter()
        thumbnailRecyclerView.adapter = thumbnailAdapter

        binding.botonSalir.setOnClickListener {
            finish()
        }

    }
}