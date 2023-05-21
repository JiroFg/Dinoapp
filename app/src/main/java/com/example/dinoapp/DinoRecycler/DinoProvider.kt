package com.example.dinoapp.DinoRecycler

import android.util.Log
import com.example.dinoapp.APIService
import com.example.dinoapp.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DinoProvider(){
    companion object {
        fun cargarDinos() {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://dinoapi-bior.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create<APIService>(APIService::class.java)
            service.getDinos().enqueue(object : Callback<List<Dino>> {
                override fun onResponse(call: Call<List<Dino>>, response: Response<List<Dino>>) {
                    val dinos = response.body()
                    dinos?.forEach {
                        Log.d("PRUEBA", it.toString())
                        HomeActivity.dinoData.add(it)
                    }
                }
                override fun onFailure(call: Call<List<Dino>>, t: Throwable) {
                    t?.printStackTrace()
                }
            })
        }
    }
}