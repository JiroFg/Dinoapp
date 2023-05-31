package com.example.dinoapp.Galeria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.dinoapp.DinoInfoActivity
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ActivityGaleriaBinding

class GaleriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGaleriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGaleriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

// Configurar el ViewPager2
        val adapter = GalleryPagerAdapter()
        viewPager.adapter = adapter

// Configurar el RecyclerView de las miniaturas
        val thumbnailAdapter = ThumbnailAdapter()

        binding.botonSalir.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DinoInfoActivity.galleryData.clear()
    }
}