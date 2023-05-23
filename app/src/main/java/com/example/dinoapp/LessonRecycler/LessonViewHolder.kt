package com.example.dinoapp.LessonRecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoapp.databinding.ItemLessonBinding

class LessonViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemLessonBinding.bind(view)

    fun render(lessonModel: Lesson, onClickListener:(Lesson)->Unit){
        binding.btn.isClickable = true
        binding.btn.isEnabled = true
        binding.btn.text = lessonModel.idTupla.toString()
        binding.btn.setOnClickListener { onClickListener(lessonModel) }
    }

    fun lockRender(lessonModel: Lesson){
        binding.btn.text = lessonModel.idTupla.toString()
        binding.btn.isClickable = false
        binding.btn.isEnabled = false
    }

}