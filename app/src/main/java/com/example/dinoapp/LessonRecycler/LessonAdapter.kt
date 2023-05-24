package com.example.dinoapp.LessonRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoapp.MainActivity
import com.example.dinoapp.R

class LessonAdapter(private val lessonList:List<Lesson>, private val onClickListener:(Lesson)-> Unit) : RecyclerView.Adapter<LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LessonViewHolder(layoutInflater.inflate(R.layout.item_lesson, parent, false))
    }

    override fun getItemCount(): Int = lessonList.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val item = lessonList[position]
        //si nivel es menor o igual al nivel del usuario entonces renderiza el nivel desbloqueado
        if(item.idTupla<=MainActivity.prefs.getLvl()) {
            holder.render(item, onClickListener)
            //por el contrario si el nivel es mayor lo pinta bloqueado
        }else{
            holder.lockRender(item)
        }
    }
}