package com.example.dinoapp.LessonRecycler

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ItemLessonBinding

class LessonViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemLessonBinding.bind(view)

    fun render(lessonModel: Lesson, onClickListener:(Lesson)->Unit){
        val reciduo:Int = lessonModel.idTupla % 2
        if(reciduo==0){
            binding.imgLeft.setImageResource(R.drawable.dino_card_1)
            binding.imgRight.setImageResource(0)
            binding.imgLeft.layoutParams.height = 240
            binding.imgLeft.layoutParams.width = 240
        }else if(reciduo == 1){
            binding.imgRight.setImageResource(R.drawable.dino_card_2)
            binding.imgLeft.setImageResource(0)
            binding.imgRight.layoutParams.height = 240
            binding.imgRight.layoutParams.width = 240
        }
        //saber que actividad es
        if(lessonModel.actividad == 1){
            binding.btnImg.setImageResource(R.drawable.ic_memoria)
            binding.btnImg.layoutParams.height = 100
            binding.btnImg.layoutParams.height = 100
            binding.btn.setCardBackgroundColor(ContextCompat.getColor(binding.btn.context,R.color.order))
        }else if(lessonModel.actividad == 2){
            binding.btnImg.setImageResource(R.drawable.ic_quiz)
            binding.btnImg.layoutParams.height = 100
            binding.btnImg.layoutParams.height = 100
            binding.btn.setCardBackgroundColor(ContextCompat.getColor(binding.btn.context,R.color.purple_200))
        }
        binding.btn.isClickable = true
        binding.btn.isEnabled = true
        binding.btn.setOnClickListener { onClickListener(lessonModel) }
        binding.btn.elevation = 18F
    }

    fun lockRender(lessonModel: Lesson){
        val reciduo:Int = lessonModel.idTupla % 2
        if(reciduo==0){
            binding.imgLeft.setImageResource(R.drawable.dino_card_1)
            binding.imgRight.setImageResource(0)
            binding.imgLeft.layoutParams.height = 240
            binding.imgLeft.layoutParams.width = 240
        }else if(reciduo == 1){
            binding.imgRight.setImageResource(R.drawable.dino_card_2)
            binding.imgLeft.setImageResource(0)
            binding.imgRight.layoutParams.height = 240
            binding.imgRight.layoutParams.width = 240
        }
        binding.btnImg.setImageResource(R.drawable.ic_lock)
        binding.btnImg.layoutParams.height = 100
        binding.btnImg.layoutParams.height = 100
        binding.btn.isClickable = false
        binding.btn.isEnabled = false
        binding.btn.setCardBackgroundColor(ContextCompat.getColor(binding.btn.context,R.color.lock_background))
        binding.btn.elevation = 0F
    }

}