package com.example.dinoapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import com.bumptech.glide.Glide
import com.example.dinoapp.Galeria.GaleriaActivity
import com.example.dinoapp.Galeria.GalleryItem
import com.example.dinoapp.Galeria.GalleryProvider
import com.example.dinoapp.databinding.ActivityDinoInfoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

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
        var galleryData = mutableListOf<GalleryItem>()
    }

    private lateinit var binding: ActivityDinoInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra(DINO_ID,0)
        val nombre = intent.getStringExtra(DINO_NOMBRE)
        val dieta = intent.getStringExtra(DINO_DIETA)
        val epoca = intent.getStringExtra(DINO_EPOCA)
        val tipo = intent.getStringExtra(DINO_TIPO)
        val orden = intent.getStringExtra(DINO_ORDEN)
        val familia = intent.getStringExtra(DINO_FAMILIA)
        val peso = intent.getFloatExtra(DINO_PESO, 0F)
        val long = intent.getFloatExtra(DINO_LONG,0F)
        val img = intent.getStringExtra(DINO_IMG)
        CoroutineScope(Dispatchers.IO).apply{
            GalleryProvider.cargarGallery(id)
        }
        Log.d("PRUEBA", galleryData.toString())

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
        binding.backButton.setOnClickListener { finish() }
        binding.galeria.setOnClickListener {
            if(isNetworkAvailable()) {
                val intent = Intent(this, GaleriaActivity::class.java)
                startActivity(intent)
            }else{
                showDialog()
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
    }

    fun drawLayout() {
        if (!isNetworkAvailable()) {
            showDialog()
        }
    }

    private fun showDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.error_conexion)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogButton: Button = dialog.findViewById(R.id.dialog_button)
        dialogButton.setOnClickListener {
            if (isNetworkAvailable()) {
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}