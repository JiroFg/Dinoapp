package com.example.dinoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    }
}