package com.example.dinoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.dinoapp.databinding.ActivityDinoInfoBinding

class DinoInfoActivity : AppCompatActivity() {

    companion object {
        //keys
        const val DINO_ID = "dinoId"
        const val DINO_NOMBRE = "dinoNombre"
        const val DINO_DIETA = "dinoDieta"
        const val DINO_EPOCA = "dinoEpoca"
        const val DINO_TIPO = "dinoTipo"
        const val DINO_ORDEN = "dinoOrden"
        const val DINO_FAMILIA = "dinoFamilia"
        const val DINO_PESO = "dinoPeso"
        const val DINO_LONG = "dinoLongitud"
        const val DINO_IMG = "dinoImg"
    }

    private lateinit var binding: ActivityDinoInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra(DINO_NOMBRE)
        val img = intent.getStringExtra(DINO_IMG)
        binding.textName.text = nombre
        Glide.with(this).load(img).into(binding.img)
        buttonsConf()
    }

    fun buttonsConf(){
        binding.backButton.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }
}