package com.example.dinoapp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinoapp.HomeActivity
import com.example.dinoapp.LessonActivity
import com.example.dinoapp.LessonRecycler.Lesson
import com.example.dinoapp.LessonRecycler.LessonAdapter
import com.example.dinoapp.MainActivity
import com.example.dinoapp.databinding.FragmentFHomeBinding

class FHome : Fragment() {

    private var _binding: FragmentFHomeBinding? = null
    private lateinit var adapter: LessonAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFHomeBinding.inflate(inflater, container, false)
        addInformationUser()
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerLevels
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = LessonAdapter(HomeActivity.lessonData) { onItemSelected(it) }
        recyclerView.adapter = adapter
    }

    fun onItemSelected(lesson: Lesson) {
        val intent = Intent(activity, LessonActivity::class.java).apply {
            putExtra(LessonActivity.LESSON_ID, lesson.idLeccion)
            putExtra(LessonActivity.LESSON_DINO_ID, lesson.idDino)
            putExtra(LessonActivity.LESSON_INFO, lesson.info)
            putExtra(LessonActivity.LESSON_ACTIVIDAD, lesson.actividad)
            putExtra(LessonActivity.LESSON_TUPLA_ID, lesson.idTupla)
        }
        Log.d("DATOS HOME A LESSON", lesson.idTupla.toString())
        startActivity(intent)
    }

    fun addInformationUser() {
        binding.userName.text = MainActivity.prefs.getName()
        binding.textLvl.text = MainActivity.prefs.getLvl().toString()
        binding.textBone.text = MainActivity.prefs.getCoins().toString()
    }

    override fun onResume() {
        super.onResume()
        addInformationUser()
        adapter.notifyDataSetChanged()
    }
}
