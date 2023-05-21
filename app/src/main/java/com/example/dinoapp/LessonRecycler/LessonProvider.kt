package com.example.dinoapp.LessonRecycler

import android.util.Log
import com.example.dinoapp.APIService
import com.example.dinoapp.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LessonProvider {
    companion object {
        fun cargarLessons() {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://dinoapi-bior.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create<APIService>(APIService::class.java)
            service.getLecciones().enqueue(object : Callback<List<Lesson>> {
                override fun onResponse(call: Call<List<Lesson>>, response: Response<List<Lesson>>) {
                    val lessons = response.body()
                    lessons?.forEach {
                        Log.d("PRUEBA2", it.toString())
                        HomeActivity.lessonData.add(it)
                    }
                }
                override fun onFailure(call: Call<List<Lesson>>, t: Throwable) {
                    t?.printStackTrace()
                }
            })
        }
    }
}