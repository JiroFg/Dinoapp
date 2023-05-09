package com.example.dinoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dinoapp.Memoria.JuegoMemoriaActivity
import com.example.dinoapp.Quiz.QuizActivity
import com.example.dinoapp.databinding.ActivityLessonBinding

class LessonActivity : AppCompatActivity() {

    companion object {
        const val LESSON_ID = "lessonId"
        const val LESSON_DINO_ID = "lessonDinoId"
        const val LESSON_INFO = "lessonInfo"
    }

    private lateinit var binding: ActivityLessonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lessonId = intent.getIntExtra(LESSON_ID,0)
        val lessonDinoId = intent.getIntExtra(LESSON_DINO_ID, 0)
        val lessonInfo = intent.getStringExtra(LESSON_INFO)

        binding.textName.text = lessonId.toString()
        binding.textInfo.text = lessonInfo

        configBtns(lessonId)

    }

    private fun configBtns(game: Int){
        binding.backBtn.setOnClickListener { finish() }

        binding.continueBtn.setOnClickListener {
            when(game){
                1 -> {
                    val intent = Intent(this,JuegoMemoriaActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this,QuizActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this,"Opcion invalida", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}