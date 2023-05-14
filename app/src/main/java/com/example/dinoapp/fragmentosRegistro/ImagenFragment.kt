package com.example.dinoapp.fragmentosRegistro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.example.dinoapp.InterfaceTransferencia
import com.example.dinoapp.R
import com.example.dinoapp.databinding.FragmentImagenBinding
import kotlin.properties.Delegates

class ImagenFragment : Fragment() {

    companion object {
        const val IMG1 = "https://cdnb.artstation.com/p/assets/images/images/039/111/871/large/paul-pereda-trex001.jpg?1624980036"
        const val IMG2 = "https://cdna.artstation.com/p/assets/images/images/039/589/530/large/paul-pereda-dilo-001.jpg?1626341613"
        const val IMG3 = "https://cdna.artstation.com/p/assets/images/images/039/291/942/large/paul-pereda-velo001.jpg?1625499589"
        const val IMG4 = "https://cdna.artstation.com/p/assets/images/images/039/197/762/large/paul-pereda-brachi001.jpg?1625209436"
        const val IMG5 = "https://cdnb.artstation.com/p/assets/images/images/030/246/531/large/paul-pereda-igpost-002.jpg?1600041160"
        const val IMG6 = "https://cdna.artstation.com/p/assets/images/images/030/670/778/large/paul-pereda-igpost-002.jpg?1601310050"
    }

    private var _binding : FragmentImagenBinding? = null
    private val binding get() = _binding!!
    var isPressed:Boolean = false
    var isPressed2:Boolean = false
    var isPressed3:Boolean = false
    var isPressed4:Boolean = false
    var isPressed5:Boolean = false
    var isPressed6:Boolean = false
    var img: String by Delegates.observable("initialValue"){ _, _, _ ->
        binding.btn.isEnabled = true
        binding.btn.isClickable = true
    }

    @SuppressLint("ResourceAsColor")
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
            Glide.with(requireActivity())
                .asBitmap()
                .load(IMG1)
                .into(binding.imgBtn1)
            if(isPressed){
                binding.card1.setCardBackgroundColor(R.color.green)
            }else{
                binding.card1.setCardBackgroundColor(R.color.white)
            }
            exceptionButton(binding.imgBtn1)
            img = IMG1
        }

        binding.imgBtn2.setOnClickListener {
            if(isPressed2){
                binding.imgBtn2.setImageResource(R.drawable.jurassic_park)
            }else{
                Glide.with(requireActivity())
                    .asBitmap()
                    .load(IMG2)
                    .into(binding.imgBtn2)
            }
            exceptionButton(binding.imgBtn2)
            img = IMG2
        }

        binding.imgBtn3.setOnClickListener {
            if(isPressed3){
                binding.imgBtn3.setImageResource(R.drawable.jurassic_park)
            }else{
                Glide.with(requireActivity())
                    .asBitmap()
                    .load(IMG3)
                    .into(binding.imgBtn3)
            }
            exceptionButton(binding.imgBtn3)
            img = IMG3
        }

        binding.imgBtn4.setOnClickListener {
            if(isPressed4){
                binding.imgBtn4.setImageResource(R.drawable.jurassic_park)
            }else{
                Glide.with(requireActivity())
                    .asBitmap()
                    .load(IMG4)
                    .into(binding.imgBtn4)
            }
            exceptionButton(binding.imgBtn4)
            img = IMG4
        }

        binding.imgBtn5.setOnClickListener {
            if(isPressed5){
                binding.imgBtn5.setImageResource(R.drawable.jurassic_park)
            }else{
                Glide.with(requireActivity())
                    .asBitmap()
                    .load(IMG5)
                    .into(binding.imgBtn5)
            }
            exceptionButton(binding.imgBtn5)
            img = IMG5
        }

        binding.imgBtn6.setOnClickListener {
            if(isPressed6){
                binding.imgBtn6.setImageResource(R.drawable.jurassic_park)
            }else{
                Glide.with(requireActivity())
                    .asBitmap()
                    .load(IMG6)
                    .into(binding.imgBtn6)
            }
            exceptionButton(binding.imgBtn6)
            img = IMG6
        }

        binding.btn.setOnClickListener {
            myInterface.transferirImg(img)
            myInterface.guardarPrefs()
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