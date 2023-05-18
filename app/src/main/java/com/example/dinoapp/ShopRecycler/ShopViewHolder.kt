package com.example.dinoapp.ShopRecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dinoapp.DinoRecycler.DinoProvider
import com.example.dinoapp.databinding.ItemShopBinding

class ShopViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemShopBinding.bind(view)

    fun render(item: ShopItem, onClickListener: (ShopItem) -> Unit){
        val dino = DinoProvider.dinoList.filter { it.id == item.dinoID }
        Glide.with(binding.imageItemShop.context)
            .load(dino[0].img)
            .into( binding.imageItemShop)
        binding.cardViewItemShop.setOnClickListener {
            onClickListener(item)
        }
    }
}