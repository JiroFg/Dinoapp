package com.example.dinoapp.LessonRecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoapp.databinding.ItemLessonBinding

class LessonViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemLessonBinding.bind(view)

    fun render(lessonModel: Lesson, onClickListener:(Lesson)->Unit){
        binding.btn.text = lessonModel.idLeccion.toString()
        binding.btn.setOnClickListener { onClickListener(lessonModel) }
    }

}