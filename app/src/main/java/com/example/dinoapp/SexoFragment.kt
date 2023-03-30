package com.example.dinoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dinoapp.databinding.FragmentSexoBinding

class SexoFragment : Fragment() {

    private var _binding: FragmentSexoBinding? = null
    private val binding get() = _binding!!
    private var sexo: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myInterface : InterfaceTransferencia = activity as InterfaceTransferencia
        _binding = FragmentSexoBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.buttonFemale.setOnClickListener {
            sexo = true
            myInterface.transferirSexo(sexo)
        }
        binding.buttonMale.setOnClickListener {
            sexo = false
            myInterface.transferirSexo(sexo)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}