package com.example.dinoapp.fragmentosRegistro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        setBtnImg()

        binding.btn.isEnabled = false
        binding.btn.isClickable = false

        binding.imgBtn1.setOnClickListener {
            setBtnImg()
            binding.imgBtn1.setImageResource(R.drawable.jurassic_park)
            img = IMG1
        }

        binding.imgBtn2.setOnClickListener {
            setBtnImg()
            binding.imgBtn2.setImageResource(R.drawable.jurassic_park)
            img = IMG2
        }

        binding.imgBtn3.setOnClickListener {
            setBtnImg()
            binding.imgBtn3.setImageResource(R.drawable.jurassic_park)
            img = IMG3
        }

        binding.imgBtn4.setOnClickListener {
            setBtnImg()
            binding.imgBtn4.setImageResource(R.drawable.jurassic_park)
            img = IMG4
        }

        binding.imgBtn5.setOnClickListener {
            setBtnImg()
            binding.imgBtn5.setImageResource(R.drawable.jurassic_park)
            img = IMG5
        }

        binding.imgBtn6.setOnClickListener {
            setBtnImg()
            binding.imgBtn6.setImageResource(R.drawable.jurassic_park)
            img = IMG6
        }

        binding.btn.setOnClickListener {
            myInterface.transferirImg(img)
            myInterface.guardarPrefs()
        }
        return view
    }

    private fun setBtnImg() {
        Glide.with(requireActivity())
            .asBitmap()
            .load(IMG1)
            .into(binding.imgBtn1)
        Glide.with(requireActivity())
            .asBitmap()
            .load(IMG2)
            .into(binding.imgBtn2)
        Glide.with(requireActivity())
            .asBitmap()
            .load(IMG3)
            .into(binding.imgBtn3)
        Glide.with(requireActivity())
            .asBitmap()
            .load(IMG4)
            .into(binding.imgBtn4)
        Glide.with(requireActivity())
            .asBitmap()
            .load(IMG5)
            .into(binding.imgBtn5)
        Glide.with(requireActivity())
            .asBitmap()
            .load(IMG6)
            .into(binding.imgBtn6)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}