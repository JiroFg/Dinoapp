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
import com.example.dinoapp.DialogFilterFragment
import com.example.dinoapp.DinoRecycler.Dino
import com.example.dinoapp.DinoInfoActivity
import com.example.dinoapp.DinoRecycler.DinoAdapter
import com.example.dinoapp.HomeActivity
import com.example.dinoapp.databinding.FragmentFBookBinding
import java.util.Locale

class FBook : Fragment() {

    private var _binding: FragmentFBookBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DinoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFBookBinding.inflate(inflater, container, false)
        initRecyclerView()
        initSearchView()
        initBtn()
        return binding.root
    }

    private fun initBtn() {
        binding.filterBtn.setOnClickListener {
            val dialog = DialogFilterFragment()
            dialog.show(requireActivity().supportFragmentManager, "cuack")
        }
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerDinos
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = DinoAdapter(HomeActivity.dinoData) { onItemSelected(it) }
        recyclerView.adapter = adapter
    }

    private fun initSearchView() {
        binding.shareView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList(newText)
                return true
            }
        })
    }

    private fun searchList(query: String?) {
        if (query != null) {
            val searchedList = arrayListOf<Dino>()
            for (dino in HomeActivity.dinoData) {
                if (dino.nombre.lowercase().contains(query)) {
                    searchedList.add(dino)
                }
            }
            if (searchedList.isEmpty()) {
                Toast.makeText(activity, "Sin coincidencias", Toast.LENGTH_SHORT).show()
                adapter.setFilterList(searchedList)
            } else {
                adapter.setFilterList(searchedList)
            }
        }
    }

    fun filterList(filters: ArrayList<String>) {
        val filteredList = arrayListOf<Dino>()
        if (filters.isNotEmpty()) {
            for (dino in HomeActivity.dinoData) {
                for (filter in filters) {
                    when (filter) {
                        dino.dieta.lowercase(Locale.ROOT),
                        dino.epoca.lowercase(Locale.ROOT),
                        dino.tipo.lowercase(Locale.ROOT),
                        dino.orden.lowercase(Locale.ROOT),
                        dino.familia.lowercase(Locale.ROOT)
                        -> {
                            //comprueba si el dinosaurio ya se ha agrado a la lista filtrada
                            val existDino = filteredList.filter { it.id == dino.id }
                            //si no existe procede a agregarlo, por el contrario si lo encuentra ya no lo agrega
                            if(existDino.isEmpty()) {
                                filteredList.add(dino)
                            }
                        }
                    }
                }
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(activity, "Sin coincidencias", Toast.LENGTH_SHORT).show()
            adapter.setFilterList(filteredList)
        } else {
            adapter.setFilterList(filteredList)
        }
    }

    fun cleanFilters() {
        adapter.setFilterList(HomeActivity.dinoData)
    }


    private fun onItemSelected(dino: Dino) {
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