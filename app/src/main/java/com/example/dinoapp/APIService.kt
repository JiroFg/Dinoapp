package com.example.dinoapp

import com.example.dinoapp.DinoRecycler.Dino
import com.example.dinoapp.Galeria.GalleryItem
import com.example.dinoapp.LessonRecycler.Lesson
import com.example.dinoapp.Quiz.QuizPreguntaData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("dinosaurios")
    fun getDinos(): Call<List<Dino>>

    @GET("lecciones")
    fun getLecciones(): Call<List<Lesson>>

    @GET("quiz/{id}")
    fun getQuiz(@Path("id") id: Int): Call<List<QuizPreguntaData>>

    @GET("imagenes/{id}")
    fun getImagenes(@Path("id") id: Int): Call<List<GalleryItem>>
}