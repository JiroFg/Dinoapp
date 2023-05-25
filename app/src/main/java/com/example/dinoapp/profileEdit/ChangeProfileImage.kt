package com.example.dinoapp.profileEdit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.dinoapp.Prefs.Prefs
import com.example.dinoapp.R
import com.example.dinoapp.databinding.ActivityChangeProfileImageBinding
import com.example.dinoapp.fragmentosRegistro.ImagenFragment
import kotlin.properties.Delegates

class ChangeProfileImage : AppCompatActivity() {

    private lateinit var binding: ActivityChangeProfileImageBinding
    private lateinit var prefs: Prefs

    var img: String by Delegates.observable("initialValue"){ _, _, _ ->
        binding.btnCambiar.isEnabled = true
        binding.btnCambiar.isClickable = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeProfileImageBinding.inflate(layoutInflater)
        prefs = Prefs(this)

        setBtnImg()

        binding.btnCambiar.isEnabled = false
        binding.btnCambiar.isClickable = false

        binding.imgBtn1.setOnClickListener {
            setBtnImg()
            binding.imgBtn1.setImageResource(R.drawable.checkgrande)
            img = ImagenFragment.IMG1
        }

        binding.imgBtn2.setOnClickListener {
            setBtnImg()
            binding.imgBtn2.setImageResource(R.drawable.checkgrande)
            img = ImagenFragment.IMG2
        }

        binding.imgBtn3.setOnClickListener {
            setBtnImg()
            binding.imgBtn3.setImageResource(R.drawable.checkgrande)
            img = ImagenFragment.IMG3
        }

        binding.imgBtn4.setOnClickListener {
            setBtnImg()
            binding.imgBtn4.setImageResource(R.drawable.checkgrande)
            img = ImagenFragment.IMG4
        }

        binding.imgBtn5.setOnClickListener {
            setBtnImg()
            binding.imgBtn5.setImageResource(R.drawable.checkgrande)
            img = ImagenFragment.IMG5
        }

        binding.imgBtn6.setOnClickListener {
            setBtnImg()
            binding.imgBtn6.setImageResource(R.drawable.checkgrande)
            img = ImagenFragment.IMG6
        }

        binding.btnCambiar.setOnClickListener {
            prefs.editImg(img)
            finish()
        }

        binding.btnAtras.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    private fun setBtnImg() {
        Glide.with(this)
            .asBitmap()
            .load(ImagenFragment.IMG1)
            .into(binding.imgBtn1)
        Glide.with(this)
            .asBitmap()
            .load(ImagenFragment.IMG2)
            .into(binding.imgBtn2)
        Glide.with(this)
            .asBitmap()
            .load(ImagenFragment.IMG3)
            .into(binding.imgBtn3)
        Glide.with(this)
            .asBitmap()
            .load(ImagenFragment.IMG4)
            .into(binding.imgBtn4)
        Glide.with(this)
            .asBitmap()
            .load(ImagenFragment.IMG5)
            .into(binding.imgBtn5)
        Glide.with(this)
            .asBitmap()
            .load(ImagenFragment.IMG6)
            .into(binding.imgBtn6)
    }
}