package com.example.dinoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dinoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), InterfaceTransferencia {

    companion object{
        lateinit var prefs: Prefs
    }

    private lateinit var binding: ActivityMainBinding
    private var sexo: Boolean = false
    private var nombre: String = ""
    private var img: Int = 1

    private val adapter by lazy { ViewPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Instancia de la clase de preferencias
        prefs = Prefs(this)

        //si las preferencias estan por defecto "true" redirige al adapter
        if(prefs.getIntro()) {
            binding.pager.adapter = adapter
            binding.pager.isUserInputEnabled = false
        }else{
            //Caso constrario si las preferencias fueron guardadas y es false inicia la actividad principal
            iniciarActividadHome()
        }
    }

    override fun transferirSexo(sexo: Boolean) {
        this.sexo = sexo
    }

    override fun transferirNombre(nombre: String) {
        this.nombre = nombre
    }

    override fun transferirImg(img: Int) {
        this.img = img
    }

    override fun guardarPrefs() {
        prefs.saveData(sexo,nombre,img)
        prefs.editIntro(false)
        iniciarActividadHome()
    }

    override fun continuar() {
        binding.pager.currentItem++
    }

    fun iniciarActividadHome(){
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}