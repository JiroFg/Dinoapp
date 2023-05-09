package com.example.dinoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.dinoapp.databinding.FragmentPopupFilterBinding

class PopupFilterFragment : DialogFragment() {

    companion object {
        const val HERVIBORO = "herviboro"
        const val CARNIVORO = "carnivoro"
        const val TRIASICO = "triasico"
        const val JURASICO = "jurasico"
        const val CRETACICO = "cretacico"
        const val TERRESTRE = "terrestre"
        const val SAURISQUIOS = "saurisquios"
        const val ORNITISQUIOS = "ornitisquios"
    }

    private var _binding: FragmentPopupFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopupFilterBinding.inflate(inflater,container,false)
        confCB()
        binding.btn.setOnClickListener {
            val list = confCB()
            Toast.makeText(activity,list.toString(),Toast.LENGTH_SHORT).show()
            dismiss()
        }
        return binding.root
    }

    private fun confCB(): ArrayList<String>{
        val filtersList = arrayListOf<String>()
        if(binding.herviboroCB.isChecked) filtersList.add(HERVIBORO)
        if(binding.carnivoroCB.isChecked) filtersList.add(CARNIVORO)
        if(binding.triacicoCB.isChecked) filtersList.add(TRIASICO)
        if(binding.jurasicoCB.isChecked) filtersList.add(JURASICO)
        if(binding.cretacicoCB.isChecked) filtersList.add(CRETACICO)
        if(binding.terrestreCB.isChecked) filtersList.add(TERRESTRE)
        if(binding.saurisquiosCB.isChecked) filtersList.add(SAURISQUIOS)
        if(binding.ornitisquiosCB.isChecked) filtersList.add(ORNITISQUIOS)
        return filtersList
    }

}