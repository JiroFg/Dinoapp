package com.example.dinoapp.Quiz

import android.util.Log
import com.example.dinoapp.APIService
import com.example.dinoapp.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizProvider {

    companion object {
        fun cargarQuices() {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://dinoapi-bior.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create<APIService>(APIService::class.java)
            service.getQuices().enqueue(object : Callback<List<QuizPreguntaData>> {
                override fun onResponse(call: Call<List<QuizPreguntaData>>, response: Response<List<QuizPreguntaData>>) {
                    val lessons = response.body()
                    lessons?.forEach {
                        Log.d("QUICES", it.toString())
                        HomeActivity.quizData.add(it)
                    }
                }
                override fun onFailure(call: Call<List<QuizPreguntaData>>, t: Throwable) {
                    t?.printStackTrace()
                }
            })
        }
    }

}