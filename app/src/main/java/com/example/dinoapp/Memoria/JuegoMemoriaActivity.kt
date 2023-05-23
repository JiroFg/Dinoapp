package com.example.dinoapp.Memoria

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.dinoapp.LessonActivity
import com.example.dinoapp.Prefs.Prefs
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ActivityJuegoMemoriaBinding
import kotlin.random.Random

class JuegoMemoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJuegoMemoriaBinding
    private lateinit var botones: List<ImageButton>
    private lateinit var cartas: List<Carta>
    private var cartaSeleccionada: Int? = null
    private var contador = 0
    private var tamano: Int? = null
    private var idTupla = 1000
    private lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJuegoMemoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = Prefs(this)
        idTupla = intent.getIntExtra(LessonActivity.LESSON_TUPLA_ID, 0)

        binding.btnContinuar.isClickable = false
        binding.btnContinuar.isEnabled = false


        val imagenes = randomImg()

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

        binding.btnContinuar.setOnClickListener {
            if (idTupla == prefs.getLvl()) {
                prefs.editLvl(prefs.getLvl() + 1)
                //Toast.makeText(this, prefs.getLvl(),Toast.LENGTH_SHORT).show()
                Log.d("MEMORIA PRUEBA", "ID TUPLA: $idTupla LVL: ${prefs.getLvl()}")
            }
            showDialogResultado()
        }

        binding.botonSalir.setOnClickListener {
            finish()
        }

        binding.btnAyuda.setOnClickListener {
            showDialogAyuda()
        }
    }

    fun randomImg(): MutableList<Int> {
        val list = mutableListOf<Int>()
        var aux: Int = 0
        while(list.size < 6){
            when(Random.nextInt(0..10)){
                0 -> aux = R.drawable.dino_card_1
                1 -> aux = R.drawable.dino_card_2
                2 -> aux =R.drawable.dino_card_3
                3 -> aux = R.drawable.dino_card_4
                4 -> aux = R.drawable.dino_card_5
                5 -> aux = R.drawable.dino_card_6
                6 -> aux = R.drawable.dino_card_7
                7 -> aux = R.drawable.dino_card_8
                8 -> aux = R.drawable.dino_card_9
                9 -> aux = R.drawable.dino_card_10
            }
            val filter = list.filter { it == aux }
            if(filter.isEmpty()){
                list.add(aux)
            }
        }
        return list
    }

    fun Random.nextInt(range: IntRange):Int{
        return range.first + nextInt(range.last - range.first)
    }
    fun activarContinuar() {
        contador = 0
        for (carta in cartas) {
            if (carta.isMatched == true) {
                contador++
            }
            if (contador == tamano) {
                binding.btnContinuar.isClickable = true
                binding.btnContinuar.isEnabled = true
                binding.btnContinuar.setBackgroundResource(R.drawable.btn)
            }
        }
    }

    private fun actualizarModelos(pos: Int) {
        val carta = cartas[pos]
        // Errores posibles: si la carta ya está volteada
        if (carta.isFaceUp) {
            return
        }
        //0 cartas volteadas -> restaurar las cartas + voltear la carta seleccionada
        //1 carta volteada -> voltear la carta seleccionada + verificar cartas iguales
        //2 cartas volteadas -> restaurar las cartas + voltear la carta seleccionada
        if (cartaSeleccionada == null) {
            restablecer()
            cartaSeleccionada = pos
        } else {
            verificarMatch(cartaSeleccionada!!, pos)
            cartaSeleccionada = null
        }
        carta.isFaceUp = !carta.isFaceUp
    }

    private fun restablecer() {
        for (carta in cartas) {
            if (!carta.isMatched) {
                carta.isFaceUp = false
            }
        }
    }

    private fun verificarMatch(pos1: Int, pos2: Int) {
        if (cartas[pos1].id == cartas[pos2].id) {
            cartas[pos1].isMatched = true
            cartas[pos2].isMatched = true
            activarContinuar()
        }
    }

    private fun actualizarVistas() {
        cartas.forEachIndexed { index, carta ->
            val boton = botones[index]
            if (carta.isMatched) {
                boton.alpha = 0.1f
            }
            boton.setImageResource(if (carta.isFaceUp) carta.id else R.drawable.cartavolteada)
        }
    }

    private fun showDialogAyuda() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_ayuda_activity)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        val videoView: VideoView = dialog.findViewById( R.id.video )
//        val videoUri = Uri.parse("android.resource://" + packageName + "/raw/memoriavideo")
//        videoView.setVideoURI(videoUri)
//        videoView.start()
        val gif: ImageView = dialog.findViewById(R.id.gif)
        Glide.with(this).asGif().load(R.raw.memoria).into(gif)
        val dialogButton: Button = dialog.findViewById(R.id.dialog_aceptar)
        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDialogResultado() {
        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_premio_memorama)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val huesitos = dialog.findViewById<TextView>(R.id.huesitos)

        if (idTupla == prefs.getLvl()) {
            huesitos.text = 5.toString()
            prefs.editCoins(prefs.getCoins() + 5)
        } else {
            huesitos.text = 1.toString()
            prefs.editCoins(prefs.getCoins() + 2)
        }
        val dialogButton: Button = dialog.findViewById(R.id.dialog_aceptar)
        dialogButton.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }
}