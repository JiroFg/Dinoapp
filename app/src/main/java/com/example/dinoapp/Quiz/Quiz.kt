package com.example.dinoapp.Quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.dinoapp.Home
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ActivityQuizBinding
import com.example.dinoapp.fragment.FHome

// Referencias
//https://github.com/codeforlife200/Quiz_App_Kotlin
//https://www.youtube.com/watch?v=sv6tsU3mlHg

class Quiz : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding

    private var score:Int =0
    private var currentpos:Int = 1
    private var listaPreguntas:ArrayList<QuizPreguntaData>?=null
    private var opcionSeleccionada:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listaPreguntas = QuizSetData.agarrarPregunta()
        setPregunta()

        binding.btnOpc1.setOnClickListener{
             opcionSeleccionadaStyle(binding.btnOpc1,1)
        }

        binding.btnOpc2.setOnClickListener{
            opcionSeleccionadaStyle(binding.btnOpc2,2)
        }

        binding.btnOpc3.setOnClickListener{
            opcionSeleccionadaStyle(binding.btnOpc3,3)
        }

        binding.btnOpc4.setOnClickListener{
            opcionSeleccionadaStyle(binding.btnOpc4,4)
        }

        binding.btnContinuar.setOnClickListener {
            if (opcionSeleccionada!=0){
                val pregunta = listaPreguntas!![currentpos-1]
                if (opcionSeleccionada!=pregunta.correcta){
                    setColor(opcionSeleccionada,R.drawable.opcion_pregunta_incorrecta)
                }else{
                    score++
                }
                setColor(pregunta.correcta,R.drawable.opcion_pregunta_correcta)
                if (opcionSeleccionada==listaPreguntas!!.size){
                    binding.btnContinuar.text = "Terminar"
                }else{
                    binding.btnContinuar.text = "Ir siguiente"
                }
            }else{
                currentpos++
                when{
                    currentpos<=listaPreguntas!!.size->{
                        setPregunta()
                    }
                    else->{
//                        Agregar el intent de la ventana de los resultados de las preguntas
//                        var intent = Intent(this,ResultadosQuiz::class.java)
//                        intent.putExtra(QuizSetData.score,score.toString())
//                        intent.putExtra("Total",listaPreguntas!!.size.toString())
                        Toast.makeText(this,score.toString(),Toast.LENGTH_SHORT).show()
                        Toast.makeText(this,listaPreguntas!!.size.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
            opcionSeleccionada=0
        }

        binding.btnAtras.setOnClickListener{
            Toast.makeText(this,"click", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

//        binding.btnContinuar.setOnClickListener {
//            if (binding.btnContinuar.text=="Terminar"){
//                val intent = Intent(this, Home::class.java)
//                startActivity(intent)
//            }
//        }
    }

    fun setColor(opt:Int,color:Int){
        when(opt){
            1->{
                binding.btnOpc1.background = ContextCompat.getDrawable(this,color)
            }
            2->{
                binding.btnOpc2.background = ContextCompat.getDrawable(this,color)
            }
            3->{
                binding.btnOpc3.background = ContextCompat.getDrawable(this,color)
            }
            4->{
                binding.btnOpc4.background = ContextCompat.getDrawable(this,color)
            }
        }
    }

    fun setPregunta(){
        val pregunta = listaPreguntas!![currentpos-1]
        setOptionStyle()
        binding.progressBar.progress = currentpos
        binding.progressBar.max = listaPreguntas!!.size
        binding.totalPreguntasNum.text = "${currentpos}"+"/"+"${binding.progressBar.max}"
        binding.preguntaTexto.text = pregunta.pregunta
        binding.btnOpc1.text = pregunta.opcion_uno
        binding.btnOpc2.text = pregunta.opcion_dos
        binding.btnOpc3.text = pregunta.opcion_tres
        binding.btnOpc4.text = pregunta.opcion_cuatro
    }

    fun setOptionStyle(){
        var listaOpciones:ArrayList<TextView> = arrayListOf()
        listaOpciones.add(0,binding.btnOpc1)
        listaOpciones.add(1,binding.btnOpc2)
        listaOpciones.add(2,binding.btnOpc3)
        listaOpciones.add(3,binding.btnOpc4)

        for (op in listaOpciones){
            op.setTextColor(Color.parseColor("#555151"))
            op.background = ContextCompat.getDrawable(this, R.drawable.opcion_pregunta)
            op.typeface = Typeface.DEFAULT
        }
    }

    fun opcionSeleccionadaStyle(view: TextView,opt:Int){
        setOptionStyle()
        opcionSeleccionada=opt
        view.background = ContextCompat.getDrawable(this, R.drawable.opcion_pregunta_seleccionada)
        view.typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))

    }
}