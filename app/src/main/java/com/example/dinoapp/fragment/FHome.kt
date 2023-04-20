package com.example.dinoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dinoapp.databinding.FragmentFHomeBinding
class FHome : Fragment() {

    private var _binding : FragmentFHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.botonMemorama.setOnClickListener {
            Toast.makeText(context,"click",Toast.LENGTH_SHORT).show()

        }


        return binding.root
    }
}
