package com.example.dinoapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinoapp.Dino
import com.example.dinoapp.DinoInfoActivity
import com.example.dinoapp.DinoProvider
import com.example.dinoapp.adapter.DinoAdapter
import com.example.dinoapp.databinding.FragmentFBookBinding

class FBook : Fragment() {

    private var _binding : FragmentFBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFBookBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView(){
        val recyclerView = binding.recyclerDinos
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DinoAdapter(DinoProvider.dinoList){onItemSelected(it)}
    }

    private fun onItemSelected(dino:Dino){
        val intent = Intent(activity, DinoInfoActivity::class.java).apply {
            putExtra(DinoInfoActivity.DINO_ID, dino.id)
            putExtra(DinoInfoActivity.DINO_NOMBRE, dino.nombre)
            putExtra(DinoInfoActivity.DINO_DIETA, dino.dieta)
            putExtra(DinoInfoActivity.DINO_EPOCA, dino.epoca)
            putExtra(DinoInfoActivity.DINO_TIPO, dino.tipo)
            putExtra(DinoInfoActivity.DINO_ORDEN, dino.orden)
            putExtra(DinoInfoActivity.DINO_FAMILIA, dino.familia)
            putExtra(DinoInfoActivity.DINO_PESO, dino.peso)
            putExtra(DinoInfoActivity.DINO_LONG, dino.longitud)
            putExtra(DinoInfoActivity.DINO_IMG, dino.img)
        }
        startActivity(intent)
    }

}