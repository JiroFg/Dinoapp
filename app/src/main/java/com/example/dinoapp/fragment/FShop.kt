package com.example.dinoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dinoapp.databinding.FragmentFShopBinding
class FShop : Fragment() {

    private var _binding: FragmentFShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFShopBinding.inflate(inflater,container,false)
        binding.btnRuleta.setOnClickListener {
            val randomNumber = (1..1000).random()

            when(randomNumber) {
                in 1..600 -> Toast.makeText(context, "Comun", Toast.LENGTH_SHORT).show()
                in 601..800 -> Toast.makeText(context, "No común", Toast.LENGTH_SHORT).show()
                in 801..949 -> Toast.makeText(context, "Raro", Toast.LENGTH_SHORT).show()
                in 950..1000 -> Toast.makeText(context, "Legendario", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}