package com.example.dinoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoapp.Dino
import com.example.dinoapp.R

class DinoAdapter(private val dinoList:List<Dino>, private val onClickListener: (Dino) -> Unit) : RecyclerView.Adapter<DinoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DinoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DinoViewHolder(layoutInflater.inflate(R.layout.item_dino, parent, false))
    }

    override fun getItemCount(): Int  = dinoList.size

    override fun onBindViewHolder(holder: DinoViewHolder, position: Int) {
        val item = dinoList[position]
        holder.render(item, onClickListener)
    }
}