package com.example.dinoapp.fragmentosRegistro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dinoapp.InterfaceTransferencia
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

        binding.buttonContinue.isEnabled = false
        binding.buttonContinue.isClickable = false

        binding.buttonFemale.setOnClickListener {
            sexo = true
            binding.buttonContinue.isEnabled = true
            binding.buttonContinue.isClickable = true
            binding.buttonFemale.isEnabled = false
            binding.buttonFemale.isClickable = false
            binding.buttonMale.isEnabled = true
            binding.buttonMale.isClickable = true
        }
        binding.buttonMale.setOnClickListener {
            sexo = false
            binding.buttonContinue.isEnabled = true
            binding.buttonContinue.isClickable = true
            binding.buttonMale.isEnabled = false
            binding.buttonMale.isClickable = false
            binding.buttonFemale.isEnabled = true
            binding.buttonFemale.isClickable = true
        }

        binding.buttonContinue.setOnClickListener {
            myInterface.transferirSexo(sexo)
            myInterface.continuar()
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}