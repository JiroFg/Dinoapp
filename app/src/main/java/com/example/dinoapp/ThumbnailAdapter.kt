package com.example.dinoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ThumbnailAdapter : RecyclerView.Adapter<ThumbnailAdapter.ViewHolder>() {

    private val images = listOf(
        R.drawable.dilo,
        R.drawable.trex,
        R.drawable.raptor,
        // Agrega más miniaturas aquí
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.miniaturas_galeria, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageResId = images[position]
        val imageView = holder.itemView.findViewById<ImageView>(R.id.miniatura)
        imageView.setImageResource(imageResId)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}
