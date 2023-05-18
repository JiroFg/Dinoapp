package com.example.dinoapp.ShopRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoapp.R

class ShopAdapter(private var shopList:List<ShopItem>, private val onClickListener: (ShopItem)->Unit): RecyclerView.Adapter<ShopViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ShopViewHolder(layoutInflater.inflate(R.layout.item_shop, parent, false))
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val item = shopList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = shopList.size
}