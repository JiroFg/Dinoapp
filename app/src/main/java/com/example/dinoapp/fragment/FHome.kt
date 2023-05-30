package com.example.dinoapp.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinoapp.AyudaActivity
import com.example.dinoapp.HomeActivity
import com.example.dinoapp.LessonActivity
import com.example.dinoapp.LessonRecycler.Lesson
import com.example.dinoapp.LessonRecycler.LessonAdapter
import com.example.dinoapp.MainActivity
import com.example.dinoapp.R
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

        binding.btnAyuda.setOnClickListener {
            val intent = Intent(context, AyudaActivity::class.java)
            startActivity(intent)
        }

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

    private fun showDialogNuevo() {
        val dialog = Dialog(requireContext())
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_new_dino)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dialogButton: Button = dialog.findViewById(R.id.dialog_aceptar)
        dialogButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        addInformationUser()
        adapter.notifyDataSetChanged()

        try{
            if (HomeActivity.boolNewDino){
                var price = HomeActivity.dinoData.minByOrNull { it.precio }
//                Toast.makeText(context,price!!.precio.toString(),Toast.LENGTH_SHORT).show()
                val coins = MainActivity.prefs.getCoins()
                if (coins > price!!.precio){
                    showDialogNuevo()
                    HomeActivity.boolNewDino = false
                }
            }
        }catch (e: Exception){
//            print(e)
            e.printStackTrace()
        }
    }
}