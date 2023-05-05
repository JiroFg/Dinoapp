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
        val dieta = intent.getStringExtra(DINO_DIETA)
        val epoca = intent.getStringExtra(DINO_EPOCA)
        val tipo = intent.getStringExtra(DINO_TIPO)
        val orden = intent.getStringExtra(DINO_ORDEN)
        val familia = intent.getStringExtra(DINO_FAMILIA)
        val peso = intent.getFloatExtra(DINO_PESO, 0F)
        val long = intent.getFloatExtra(DINO_LONG,0F)
        val img = intent.getStringExtra(DINO_IMG)

        binding.textName.text = nombre
        binding.dietaText.text = dieta
        binding.epocaText.text = epoca
        binding.tipoText.text = tipo
        binding.ordenText.text = orden
        binding.familiaText.text = familia
        val pesoStr = peso.toString()
        val longStr = long.toString()
        binding.pesoText.text = "$pesoStr t"
        binding.longText.text = "$longStr m"
        Glide.with(this).load(img).into(binding.img)

        buttonsConf()
    }

    fun buttonsConf(){
        binding.backButton.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }
}