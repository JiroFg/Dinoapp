package com.example.dinoapp.profileEdit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dinoapp.Prefs.Prefs
import com.example.dinoapp.databinding.ActivityChangeNameBinding

class Change_name : AppCompatActivity() {

    private lateinit var binding: ActivityChangeNameBinding
    private lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeNameBinding.inflate(layoutInflater)
        prefs = Prefs(this)
        changeName()
        setContentView(binding.root)
    }
    fun changeName() {
        binding.buttonChange.setOnClickListener{
            if (binding.inputEditName.text.toString().isNotEmpty()) {
                prefs.editName(binding.inputEditName.text.toString())
                finish()
            }else {
                Toast.makeText(this, "Ingresa un nombre", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAtras.setOnClickListener {
            finish()
        }
    }
}