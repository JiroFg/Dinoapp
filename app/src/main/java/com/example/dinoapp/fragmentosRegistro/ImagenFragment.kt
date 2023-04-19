package com.example.dinoapp.fragmentosRegistro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.dinoapp.InterfaceTransferencia
import com.example.dinoapp.R
import com.example.dinoapp.databinding.FragmentImagenBinding
import com.google.android.material.circularreveal.CircularRevealHelper.Delegate
import kotlin.properties.Delegates

class ImagenFragment : Fragment() {

    private var _binding : FragmentImagenBinding? = null
    private val binding get() = _binding!!
    var isPressed:Boolean = false
    var isPressed2:Boolean = false
    var isPressed3:Boolean = false
    var isPressed4:Boolean = false
    var isPressed5:Boolean = false
    var isPressed6:Boolean = false
    var img: Int by Delegates.observable(0){ prop, old, new ->
        binding.btn.isEnabled = true
        binding.btn.isClickable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myInterface : InterfaceTransferencia = activity as InterfaceTransferencia
        _binding = FragmentImagenBinding.inflate(inflater, container,false)
        val view = binding.root
        //jeje
        binding.btn.isEnabled = false
        binding.btn.isClickable = false
        //Activar y desactivar image buttons
        binding.imgBtn1.setOnClickListener {
            if(isPressed){
                binding.imgBtn1.setImageResource(R.drawable.jurassic_park)
            }else{
                binding.imgBtn1.setImageResource(R.drawable.ic_launcher_foreground)
            }
            //isPressed = !isPressed
            exceptionButton(binding.imgBtn1)
            img = 1
        }
        binding.imgBtn2.setOnClickListener {
            if(isPressed2){
                binding.imgBtn2.setImageResource(R.drawable.jurassic_park)
            }else{
                binding.imgBtn2.setImageResource(R.drawable.ic_launcher_foreground)
            }
            //isPressed2 = !isPressed2
            exceptionButton(binding.imgBtn2)
            img = 2
        }
        binding.imgBtn3.setOnClickListener {
            if(isPressed3){
                binding.imgBtn3.setImageResource(R.drawable.jurassic_park)
            }else{
                binding.imgBtn3.setImageResource(R.drawable.ic_launcher_foreground)
            }
            //isPressed3 = !isPressed3
            exceptionButton(binding.imgBtn3)
            img = 3
        }
        binding.imgBtn4.setOnClickListener {
            if(isPressed4){
                binding.imgBtn4.setImageResource(R.drawable.jurassic_park)
            }else{
                binding.imgBtn4.setImageResource(R.drawable.ic_launcher_foreground)
            }
            //isPressed4 = !isPressed4
            exceptionButton(binding.imgBtn4)
            img = 4
        }
        binding.imgBtn5.setOnClickListener {
            if(isPressed5){
                binding.imgBtn5.setImageResource(R.drawable.jurassic_park)
            }else{
                binding.imgBtn5.setImageResource(R.drawable.ic_launcher_foreground)
            }
            //isPressed5 = !isPressed5
            exceptionButton(binding.imgBtn5)
            img = 5
        }
        binding.imgBtn6.setOnClickListener {
            if(isPressed6){
                binding.imgBtn6.setImageResource(R.drawable.jurassic_park)
            }else{
                binding.imgBtn6.setImageResource(R.drawable.ic_launcher_foreground)
            }
            //isPressed6 = !isPressed6
            exceptionButton(binding.imgBtn6)
            img = 6
        }

        binding.btn.setOnClickListener {
            myInterface.transferirImg(img)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun exceptionButton(boton:ImageButton){
        if(boton != binding.imgBtn1){
            binding.imgBtn1.setImageResource(R.drawable.jurassic_park)
            isPressed = false
        }
        if(boton != binding.imgBtn2){
            binding.imgBtn2.setImageResource(R.drawable.jurassic_park)
            isPressed2 = false
        }
        if(boton != binding.imgBtn3){
            binding.imgBtn3.setImageResource(R.drawable.jurassic_park)
            isPressed3 = false
        }
        if(boton != binding.imgBtn4){
            binding.imgBtn4.setImageResource(R.drawable.jurassic_park)
            isPressed4 = false
        }
        if(boton != binding.imgBtn5){
            binding.imgBtn5.setImageResource(R.drawable.jurassic_park)
            isPressed5 = false
        }
        if(boton != binding.imgBtn6){
            binding.imgBtn6.setImageResource(R.drawable.jurassic_park)
            isPressed6 = false
        }
    }
}