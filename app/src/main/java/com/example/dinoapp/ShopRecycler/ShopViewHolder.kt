package com.example.dinoapp.ShopRecycler

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dinoapp.DinoRecycler.Dino
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ItemShopBinding

class ShopViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemShopBinding.bind(view)

    fun render(item: Dino, onClickListener: (Dino) -> Unit){
        Glide.with(binding.imageItemShop.context)
            .load(item.img)
            .into( binding.imageItemShop)
        binding.imageItemShop.setBackgroundColor(ContextCompat.getColor(binding.imageItemShop.context, R.color.white))
        binding.textPrecio.text = "$${item.precio}"
        binding.itemShop.setOnClickListener {
            onClickListener(item)
        }
    }

    fun unlockRender(){
        binding.imageItemShop.setImageResource(R.drawable.ic_check)
        binding.imageItemShop.setPadding(56)
        binding.imageItemShop.setBackgroundColor(ContextCompat.getColor(binding.imageItemShop.context, R.color.green))
        binding.textPrecio.text = binding.textPrecio.context.getString(R.string.purchased)
    }
}