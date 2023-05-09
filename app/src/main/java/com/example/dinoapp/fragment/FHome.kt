package com.example.dinoapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinoapp.LessonActivity
import com.example.dinoapp.LessonRecycler.Lesson
import com.example.dinoapp.LessonRecycler.LessonAdapter
import com.example.dinoapp.LessonRecycler.LessonProvider
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

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerLevels
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = LessonAdapter(LessonProvider.lessonList) { onItemSelected(it) }
    }

    fun onItemSelected(lesson: Lesson) {
        val intent = Intent(activity, LessonActivity::class.java).apply {
            putExtra(LessonActivity.LESSON_ID, lesson.ID)
            putExtra(LessonActivity.LESSON_DINO_ID, lesson.dinoID)
            putExtra(LessonActivity.LESSON_INFO, lesson.info)
        }
        startActivity(intent)
    }
}
