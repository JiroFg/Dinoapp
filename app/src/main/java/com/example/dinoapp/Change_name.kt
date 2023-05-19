package com.example.dinoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dinoapp.MainActivity.Companion.prefs
import com.example.dinoapp.databinding.ActivityChangeNameBinding

class Change_name : AppCompatActivity() {

    private lateinit var binding: ActivityChangeNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeName()
    }
    fun changeName() {
        binding.buttonChange.setOnClickListener{
            if (binding.inputEditName.text.toString().isNotEmpty()) {
                prefs.editName(binding.inputEditName.text.toString())
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
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