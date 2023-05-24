package com.example.dinoapp.LessonRecycler

import android.view.View
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
        binding.btn.isClickable = true
        binding.btn.isEnabled = true
        binding.textBtn.text = lessonModel.idTupla.toString()
        binding.btn.setOnClickListener { onClickListener(lessonModel) }
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
        binding.textBtn.text = lessonModel.idTupla.toString()
        binding.btn.isClickable = false
        binding.btn.isEnabled = false
    }

}