package com.example.dinoapp

import com.example.dinoapp.DinoRecycler.Dino
import com.example.dinoapp.LessonRecycler.Lesson
import com.example.dinoapp.Quiz.QuizPreguntaData
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("dinosaurios")
    fun getDinos(): Call<List<Dino>>

    @GET("lecciones")
    fun getLecciones(): Call<List<Lesson>>

    @GET("quiz")
    fun getQuices(): Call<List<QuizPreguntaData>>
}