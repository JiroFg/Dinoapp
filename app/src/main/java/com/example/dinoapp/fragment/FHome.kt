package com.example.dinoapp.fragment

import android.content.Intent
import android.os.Bundle
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
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        recyclerView.adapter = LessonAdapter(HomeActivity.lessonData) { onItemSelected(it) }
    }

    fun onItemSelected(lesson: Lesson) {
        val intent = Intent(activity, LessonActivity::class.java).apply {
            putExtra(LessonActivity.LESSON_ID, lesson.idLeccion)
            putExtra(LessonActivity.LESSON_DINO_ID, lesson.idDino)
            putExtra(LessonActivity.LESSON_INFO, lesson.info)
        }
        startActivity(intent)
    }

    fun addInformationUser() {
        binding.name.text = MainActivity.prefs.getName()
    }
}
