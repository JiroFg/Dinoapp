package com.example.dinoapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import com.example.dinoapp.Prefs.Prefs
import com.example.dinoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), InterfaceTransferencia {

    companion object{
        lateinit var prefs: Prefs
    }

    private lateinit var binding: ActivityMainBinding
    private var sexo: Boolean = false
    private var nombre: String = ""
    private var img: String = ""
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
            drawLayout()
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

    override fun transferirImg(img: String) {
        this.img = img
    }

    override fun guardarPrefs() {
        prefs.saveData(sexo,nombre,img)
        prefs.editCoins(0)
        prefs.editLvl(1)
        prefs.editIntro(false)
        iniciarActividadHome()
    }

    override fun continuar() {
        binding.pager.currentItem++
        drawLayout()
    }

    fun iniciarActividadHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))

    }

    private fun drawLayout() {
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