package com.example.dinoapp

import com.example.dinoapp.DinoRecycler.Dino
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("dinosaurios")
    fun getDinos(): Call<List<Dino>>
}