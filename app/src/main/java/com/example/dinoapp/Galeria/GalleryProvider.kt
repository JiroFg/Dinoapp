package com.example.dinoapp.Galeria

import android.util.Log
import com.example.dinoapp.APIService
import com.example.dinoapp.DinoInfoActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GalleryProvider {
    companion object {
        fun cargarGallery(id: Int) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://dinoapi-bior.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create<APIService>(APIService::class.java)
            service.getImagenes(id).enqueue(object : Callback<List<GalleryItem>> {
                override fun onResponse(call: Call<List<GalleryItem>>, response: Response<List<GalleryItem>>) {
                    val lessons = response.body()
                    lessons?.forEach {
                        Log.d("LESSONS", it.toString())
                        DinoInfoActivity.galleryData.add(it)
                    }
                }
                override fun onFailure(call: Call<List<GalleryItem>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}