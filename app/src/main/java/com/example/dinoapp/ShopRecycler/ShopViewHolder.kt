package com.example.dinoapp.ShopRecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dinoapp.DinoRecycler.Dino
import com.example.dinoapp.databinding.ItemShopBinding

class ShopViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemShopBinding.bind(view)

    fun render(item: Dino, onClickListener: (Dino) -> Unit){
        Glide.with(binding.imageItemShop.context)
            .load(item.img)
            .into( binding.imageItemShop)
        binding.cardViewItemShop.setOnClickListener {
            onClickListener(item)
        }
    }

    fun unlockRender(){
        Glide.with(binding.imageItemShop.context)
            .load("https://cms-assets.tutsplus.com/cdn-cgi/image/width=630/uploads/users/523/posts/32694/final_image/tutorial-preview-large.png")
            .into( binding.imageItemShop)
    }
}