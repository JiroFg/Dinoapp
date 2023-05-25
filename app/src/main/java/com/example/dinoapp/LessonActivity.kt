package com.example.dinoapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.dinoapp.Memoria.JuegoMemoriaActivity
import com.example.dinoapp.Quiz.QuizActivity
import com.example.dinoapp.Quiz.QuizPreguntaData
import com.example.dinoapp.Quiz.QuizProvider
import com.example.dinoapp.databinding.ActivityLessonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LessonActivity : AppCompatActivity() {

    companion object {
        const val LESSON_ID = "lessonId"
        const val LESSON_DINO_ID = "lessonDinoId"
        const val LESSON_INFO = "lessonInfo"
        const val LESSON_ACTIVIDAD = "actividad"
        const val LESSON_TUPLA_ID = "idTupla"
        var quizData = mutableListOf<QuizPreguntaData>()
    }

    private lateinit var binding: ActivityLessonBinding
    private var idTupla = 1000
    private var lessonDinoId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawLayout()
        lessonDinoId = intent.getIntExtra(LESSON_DINO_ID, 0)
        val lessonInfo = intent.getStringExtra(LESSON_INFO)
        val actividad = intent.getIntExtra(LESSON_ACTIVIDAD, 1)
        idTupla = intent.getIntExtra(LESSON_TUPLA_ID, 0)
        Log.d("ID EN LESSON", idTupla.toString())

        val dino = HomeActivity.dinoData.filter { it.id == lessonDinoId }

        binding.textName.text = dino[0].nombre
        binding.img
        Glide.with(binding.img.context).load(dino[0].img).into(binding.img)
        binding.textInfo.text = lessonInfo

        configBtns(actividad)
        CoroutineScope(Dispatchers.IO).launch {
            quizData = QuizProvider.cargarQuiz(lessonDinoId)
        }
    }

    private fun configBtns(game: Int) {
        binding.backBtn.setOnClickListener { finish() }

        binding.continueBtn.setOnClickListener {
            if (isNetworkAvailable()) {
                when (game) {
                    1 -> {
                        val intent = Intent(this, JuegoMemoriaActivity::class.java).apply {
                            putExtra(LESSON_TUPLA_ID, idTupla)
                        }
                        startActivity(intent)
                        finish()
                    }

                    2 -> {
                        val intent = Intent(this, QuizActivity::class.java).apply {
                            putExtra(LESSON_TUPLA_ID, idTupla)
                            putExtra(LESSON_DINO_ID, lessonDinoId)
                        }
                        startActivity(intent)
                        finish()
                    }

                    else -> {
                        Toast.makeText(this, "Opcion invalida", Toast.LENGTH_SHORT).show()
                    }
                }
            }else
                drawLayout()
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
}