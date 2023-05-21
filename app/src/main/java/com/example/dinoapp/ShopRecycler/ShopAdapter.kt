package com.example.dinoapp.ShopRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoapp.DinoRecycler.Dino
import com.example.dinoapp.HomeActivity
import com.example.dinoapp.R

class ShopAdapter(private var dinoList:List<Dino>, private val onClickListener: (Dino)->Unit): RecyclerView.Adapter<ShopViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ShopViewHolder(layoutInflater.inflate(R.layout.item_shop, parent, false))
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val item = dinoList[position]
        val filter = HomeActivity.shopData.filter { it.DinoID == item.id }
        if(filter.isEmpty()){
            holder.render(item, onClickListener)
        }else{
            holder.unlockRender()
        }
    }

    override fun getItemCount(): Int = dinoList.size

    fun reloadAdapter(){
        notifyDataSetChanged()
    }
}