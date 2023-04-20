package com.example.dinoapp.Memoria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ActivityJuegoMemoriaBinding

class juegoMemoria : AppCompatActivity() {

    private lateinit var binding: ActivityJuegoMemoriaBinding
    private lateinit var botones: List<ImageButton>
    private lateinit var cartas: List<carta>
    private var cartaSeleccionada: Int? = null
    private var TAG = "logs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJuegoMemoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            carta(imagenes[index])
        }
        botones.forEachIndexed { index, boton ->
            boton.setOnClickListener {
                Log.i(TAG, "Boton pulsado")
                // Actualizar los modelos
                actualizarModelos(index)
                // Actulizar UI
                actualizarVistas()
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
}