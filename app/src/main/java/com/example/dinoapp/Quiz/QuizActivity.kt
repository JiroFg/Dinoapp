package com.example.dinoapp.Quiz

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.dinoapp.LessonActivity
import com.example.dinoapp.Prefs.Prefs
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ActivityQuizBinding

// Referencias
//https://github.com/codeforlife200/Quiz_App_Kotlin
//https://www.youtube.com/watch?v=sv6tsU3mlHg

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var prefs: Prefs

    private var score:Int =0
    private var currentpos:Int = 1
    private var listaPreguntas:ArrayList<QuizPreguntaData>?=null
    private var opcionSeleccionada:Int=0
    private var idTupla = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        idTupla = intent.getIntExtra(LessonActivity.LESSON_TUPLA_ID, 0)
        prefs = Prefs(this)
        setContentView(binding.root)

        desactivarBtn()

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
                    binding.btnOpc1.isClickable = false
                    binding.btnOpc2.isClickable = false
                    binding.btnOpc3.isClickable = false
                    binding.btnOpc4.isClickable = false
                }else{
                    binding.btnContinuar.text = "Ir siguiente"
                    binding.btnOpc1.isClickable = false
                    binding.btnOpc2.isClickable = false
                    binding.btnOpc3.isClickable = false
                    binding.btnOpc4.isClickable = false
                }
            }else{
                currentpos++
                when{
                    currentpos<=listaPreguntas!!.size->{
                        setPregunta()
                    }
                    else->{
//                        Agregar el intent de la ventana de los resultados de las preguntas
                        showDialogResultado(score)
                        if(idTupla == prefs.getLvl()){
                            prefs.editLvl(prefs.getLvl()+1)
                            //Toast.makeText(this, prefs.getLvl(),Toast.LENGTH_SHORT).show()
                            Log.d("Quiz preuba", "ID TUPLA: $idTupla LVL: ${prefs.getLvl()}")
                        }
                        //-------------Agregar datos a la BD Local ------------------//
                        //-----//
                    }
                }
            }
            opcionSeleccionada=0
        }

        binding.btnAtras.setOnClickListener{
            Toast.makeText(this,"click", Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.btnAyuda.setOnClickListener {
            showDialogAyuda()
        }

    }

    fun activarBtn(){
        binding.btnContinuar.isEnabled = true
        binding.btnContinuar.isClickable = true
        binding.btnContinuar.setBackgroundResource(R.drawable.btn)
    }

    fun desactivarBtn(){
        binding.btnContinuar.setBackgroundResource(R.drawable.bg_button_racha)
        binding.btnContinuar.text = "Continuar"
        binding.btnContinuar.isEnabled = false
        binding.btnContinuar.isClickable = false
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
        binding.btnOpc1.isClickable = true
        binding.btnOpc2.isClickable = true
        binding.btnOpc3.isClickable = true
        binding.btnOpc4.isClickable = true
        desactivarBtn()
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
        activarBtn()
    }

    private fun showDialogAyuda() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE )
        dialog.setContentView( R.layout.dialog_ayuda_activity )
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable( ColorDrawable( Color.TRANSPARENT ))
//        val videoView: VideoView = dialog.findViewById( R.id.video )
//        val videoUri = Uri.parse("android.resource://" + packageName + "/raw/memoriavideo")
//        videoView.setVideoURI(videoUri)
//        videoView.start()
        val gif: ImageView = dialog.findViewById( R.id.gif )
        Glide.with(this).asGif().load(R.raw.quiz).into(gif)
        val dialogButton : Button = dialog.findViewById( R.id.dialog_aceptar )
        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDialogResultado(resultado:Int){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_premio_quiz)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val rp = dialog.findViewById<TextView>(R.id.resultadoQuiz)
        rp.text = resultado.toString()

        val huesito = dialog.findViewById<TextView>(R.id.huesitos)
//        huesito.text = huesitos.toString()

        if(idTupla == prefs.getLvl()){
            huesito.text = (resultado*4).toString()
            prefs.editCoins(prefs.getCoins()+(resultado*4))
        }else{
            huesito.text = (resultado*2).toString()
            prefs.editCoins(prefs.getCoins()+(resultado*2))
        }

        val dialogButton: Button = dialog.findViewById(R.id.dialog_aceptar)
        dialogButton.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }
}