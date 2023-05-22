package com.example.dinoapp.DinoRecycler

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ItemDinoBinding

class DinoViewHolder(view:View): RecyclerView.ViewHolder(view){

    val binding = ItemDinoBinding.bind(view)

    fun render(dino: Dino, onClickListener:(Dino) -> Unit){
        binding.textDinoName.text = dino.nombre
        Glide.with(binding.imageDino.context).load(dino.img).into(binding.imageDino)
        binding.imageDino.setBackgroundColor(ContextCompat.getColor(binding.imageDino.context, R.color.white))
        binding.imageDino.setPadding(0)
        binding.itemDino.setOnClickListener {
            onClickListener(dino)
        }
    }

    fun lockRender(){
        binding.textDinoName.text = binding.textDinoName.context.getString(R.string.lock)
        binding.imageDino.setBackgroundColor(ContextCompat.getColor(binding.imageDino.context,R.color.lock_background))
        binding.imageDino.setImageResource(R.drawable.ic_lock)
        binding.imageDino.setPadding(56)
        //Glide.with(binding.imageDino.context).load("https://indyme.com/wp-content/uploads/2020/11/lock-icon.png").into(binding.imageDino)
    }
}