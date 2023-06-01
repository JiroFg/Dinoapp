package com.example.dinoapp.LessonRecycler

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ItemLessonBinding
import kotlin.random.Random

class LessonViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemLessonBinding.bind(view)

    fun render(lessonModel: Lesson, onClickListener:(Lesson)->Unit){
        val reciduo:Int = lessonModel.idTupla % 2
        if(reciduo==0){
            binding.imgLeft.setImageResource(randomImg())
            binding.imgRight.setImageResource(0)
            binding.imgLeft.layoutParams.height = 300
            binding.imgLeft.layoutParams.width = 300
        }else if(reciduo == 1){
            binding.imgRight.setImageResource(randomImg())
            binding.imgLeft.setImageResource(0)
            binding.imgRight.layoutParams.height = 300
            binding.imgRight.layoutParams.width = 300
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
            binding.imgLeft.setImageResource(randomImg())
            binding.imgRight.setImageResource(0)
            binding.imgLeft.layoutParams.height = 300
            binding.imgLeft.layoutParams.width = 300
        }else if(reciduo == 1){
            binding.imgRight.setImageResource(randomImg())
            binding.imgLeft.setImageResource(0)
            binding.imgRight.layoutParams.height = 300
            binding.imgRight.layoutParams.width = 300
        }
        binding.btnImg.setImageResource(R.drawable.ic_lock)
        binding.btnImg.layoutParams.height = 100
        binding.btnImg.layoutParams.height = 100
        binding.btn.isClickable = false
        binding.btn.isEnabled = false
        binding.btn.setCardBackgroundColor(ContextCompat.getColor(binding.btn.context,R.color.lock_background))
        binding.btn.elevation = 0F
    }

    fun randomImg():Int{
        val random = Random.nextInt(1..8)
        var img = 1
        when(random){
            1 -> { img = R.drawable.ic_home_1}
            2 -> { img = R.drawable.ic_home_2}
            3 -> { img = R.drawable.ic_home_3}
            4 -> { img = R.drawable.ic_home_4}
            5 -> { img = R.drawable.ic_home_5}
            6 -> { img = R.drawable.ic_home_6}
            7 -> { img = R.drawable.ic_home_7}
            8 -> { img = R.drawable.ic_home_8}
        }
        return img
    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }

}