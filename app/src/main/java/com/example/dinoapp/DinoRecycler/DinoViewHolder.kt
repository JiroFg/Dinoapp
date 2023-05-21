package com.example.dinoapp.DinoRecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dinoapp.databinding.ItemDinoBinding

class DinoViewHolder(view:View): RecyclerView.ViewHolder(view){

    val binding = ItemDinoBinding.bind(view)

    fun render(dino: Dino, onClickListener:(Dino) -> Unit){
        binding.textDinoName.text = dino.nombre
        Glide.with(binding.imageDino.context).load(dino.img).into(binding.imageDino)
        binding.itemDino.setOnClickListener {
            onClickListener(dino)
        }
    }

    fun lockRender(){
        Glide.with(binding.imageDino.context).load("https://indyme.com/wp-content/uploads/2020/11/lock-icon.png").into(binding.imageDino)
    }
}