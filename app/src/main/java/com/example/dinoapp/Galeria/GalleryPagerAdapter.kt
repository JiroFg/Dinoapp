package com.example.dinoapp.Galeria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dinoapp.DinoInfoActivity
import com.example.dinoapp.R

class GalleryPagerAdapter : RecyclerView.Adapter<GalleryPagerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.imagenes_galeria, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = DinoInfoActivity.galleryData[position]
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imageView)
        Glide.with(imageView.context).load(img.img).into(imageView)
    }

    override fun getItemCount(): Int {
        return DinoInfoActivity.galleryData.size
    }
}
