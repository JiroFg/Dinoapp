package com.example.dinoapp.Memoria

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ActivityJuegoMemoriaBinding

class JuegoMemoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJuegoMemoriaBinding
    private lateinit var botones: List<ImageButton>
    private lateinit var cartas: List<Carta>
    private var cartaSeleccionada: Int? = null
    private var contador = 0
    private var tamano: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJuegoMemoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnContinuar.isClickable = false
        binding.btnContinuar.isEnabled = false
        binding.btnContinuar.setOnClickListener {
            finish()
        }

        val imagenes = mutableListOf(
            R.drawable.memorama1,
            R.drawable.memorama2,
            R.drawable.memorama3,
            R.drawable.memorama4,
            R.drawable.jurassic_park,
            R.drawable.fire
        )

        //Agregar cada imagen dos veces para crear los pares
        imagenes.addAll(imagenes)
        //Cambiar el orden para que sea diferente
        imagenes.shuffle()
        //Lista de botones
        botones = listOf(
            binding.boton1, binding.boton2, binding.boton3, binding.boton4,
            binding.boton5, binding.boton6, binding.boton7, binding.boton8,
            binding.boton9, binding.boton10, binding.boton11, binding.boton12
        )

        cartas = botones.indices.map { index ->
            Carta(imagenes[index])
        }
        tamano = cartas.size
        botones.forEachIndexed { index, boton ->
            boton.setOnClickListener {
//                Toast.makeText(this,"click",Toast.LENGTH_SHORT).show()
                // Actualizar los modelos
                actualizarModelos(index)
                // Actulizar UI
                actualizarVistas()
            }
        }

        binding.botonSalir.setOnClickListener{
//            Toast.makeText(this,"click",Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, Home::class.java)
//            startActivity(intent)
            finish()
        }

        binding.btnAyuda.setOnClickListener {
            showDialogAyuda()
        }
    }
    fun activarContinuar(){
        contador = 0
        for (carta in cartas){
            if (carta.isMatched == true){
                contador ++
            }
            if (contador == tamano){
                binding.btnContinuar.isClickable = true
                binding.btnContinuar.isEnabled = true
                binding.btnContinuar.setBackgroundResource(R.drawable.btn)
            }
        }
    }

    private fun actualizarModelos(pos: Int) {
        val carta = cartas[pos]
        // Errores posibles: si la carta ya está volteada
        if (carta.isFaceUp){
            Toast.makeText(this,"No puedes hacer eso :(", Toast.LENGTH_SHORT).show()
            return
        }
        //0 cartas volteadas -> restaurar las cartas + voltear la carta seleccionada
        //1 carta volteada -> voltear la carta seleccionada + verificar cartas iguales
        //2 cartas volteadas -> restaurar las cartas + voltear la carta seleccionada
        if (cartaSeleccionada == null){
            restablecer()
            cartaSeleccionada = pos
        }else{
            verificarMatch(cartaSeleccionada!!, pos)
            cartaSeleccionada = null
        }
        carta.isFaceUp = !carta.isFaceUp
    }

    private fun restablecer() {
        for (carta in cartas){
            if (!carta.isMatched){
                carta.isFaceUp = false
            }
        }
    }

    private fun verificarMatch(pos1: Int, pos2: Int) {
        if (cartas[pos1].id == cartas[pos2].id){
            Toast.makeText(this,"Felicidades son iguales", Toast.LENGTH_SHORT).show()
            cartas[pos1].isMatched = true
            cartas[pos2].isMatched = true
            activarContinuar()
        }
    }

    private fun actualizarVistas() {
        cartas.forEachIndexed{index, carta ->
            val boton = botones[index]
            if (carta.isMatched){
                boton.alpha = 0.1f
            }
            boton.setImageResource(if (carta.isFaceUp) carta.id else R.drawable.cartavolteada)
        }
    }

    private fun showDialogAyuda() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE )
        dialog.setContentView( R.layout.dialog_ayuda_activity )
        dialog.window?.setBackgroundDrawable( ColorDrawable( Color.TRANSPARENT ))
//        val videoView: VideoView = dialog.findViewById( R.id.video )
//        val videoUri = Uri.parse("android.resource://" + packageName + "/raw/memoriavideo")
//        videoView.setVideoURI(videoUri)
//        videoView.start()
        val gif: ImageView = dialog.findViewById( R.id.gif )
        Glide.with(this).asGif().load(R.raw.memoria).into(gif)
        val dialogButton : Button = dialog.findViewById( R.id.dialog_aceptar )
        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}