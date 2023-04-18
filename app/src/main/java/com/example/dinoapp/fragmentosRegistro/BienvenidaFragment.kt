package com.example.dinoapp.fragmentosRegistro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dinoapp.InterfaceTransferencia
import com.example.dinoapp.databinding.FragmentBienvenidaBinding

class BienvenidaFragment : Fragment() {

    private var _binding: FragmentBienvenidaBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myInterface : InterfaceTransferencia = activity as InterfaceTransferencia
        _binding = FragmentBienvenidaBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.buttonContinue.setOnClickListener {
            myInterface.continuar()
        }
        return view
    }
}