package com.example.dinoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.dinoapp.Memoria.JuegoMemoriaActivity
import com.example.dinoapp.Quiz.QuizActivity
import com.example.dinoapp.databinding.ActivityLessonBinding

class LessonActivity : AppCompatActivity() {

    companion object {
        const val LESSON_ID = "lessonId"
        const val LESSON_DINO_ID = "lessonDinoId"
        const val LESSON_INFO = "lessonInfo"
        const val LESSON_ACTIVIDAD = "actividad"
        const val LESSON_TUPLA_ID = "idTupla"
    }

    private lateinit var binding: ActivityLessonBinding
    private var idTupla = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lessonDinoId = intent.getIntExtra(LESSON_DINO_ID, 0)
        val lessonInfo = intent.getStringExtra(LESSON_INFO)
        val actividad = intent.getIntExtra(LESSON_ACTIVIDAD,1)
        idTupla = intent.getIntExtra(LESSON_TUPLA_ID, 0)
        Log.d("ID EN LESSON", idTupla.toString())

        val dino = HomeActivity.dinoData.filter { it.id == lessonDinoId }

        binding.textName.text = dino[0].nombre
        binding.img
        Glide.with(binding.img.context).load(dino[0].img).into(binding.img)
        binding.textInfo.text = lessonInfo

        configBtns(actividad)

    }

    private fun configBtns(game: Int){
        binding.backBtn.setOnClickListener { finish() }

        binding.continueBtn.setOnClickListener {
            when(game){
                1 -> {
                    val intent = Intent(this,JuegoMemoriaActivity::class.java).apply {
                        putExtra(LESSON_TUPLA_ID, idTupla)
                    }
                    startActivity(intent)
                    finish()
                }
                2 -> {
                    val intent = Intent(this,QuizActivity::class.java).apply {
                        putExtra(LESSON_TUPLA_ID, idTupla)
                    }
                    startActivity(intent)
                    finish()
                }
                else -> {
                    Toast.makeText(this,"Opcion invalida", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}