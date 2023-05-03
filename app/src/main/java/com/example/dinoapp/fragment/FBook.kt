package com.example.dinoapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dinoapp.Dino
import com.example.dinoapp.DinoInfoActivity
import com.example.dinoapp.DinoProvider
import com.example.dinoapp.adapter.DinoAdapter
import com.example.dinoapp.databinding.FragmentFBookBinding
import java.util.Locale

class FBook : Fragment() {

    private var _binding : FragmentFBookBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DinoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFBookBinding.inflate(inflater, container, false)
        initRecyclerView()
        initSearchView()
        return binding.root
    }

    private fun initRecyclerView(){
        val recyclerView = binding.recyclerDinos
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = DinoAdapter(DinoProvider.dinoList){onItemSelected(it)}
        recyclerView.adapter = adapter
    }

    private fun initSearchView(){
        binding.shareView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query:String?){
        if(query!=null){
            val filteredList = arrayListOf<Dino>()
            for(dino in DinoProvider.dinoList){
                if(dino.nombre.lowercase().contains(query)){
                    filteredList.add(dino)
                }
            }
            if(filteredList.isEmpty()){
                Toast.makeText(activity, "Sin coincidencias",Toast.LENGTH_SHORT).show()
                adapter.setFilterList(filteredList)
            }else{
                adapter.setFilterList(filteredList)
            }
        }
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