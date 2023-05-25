package com.example.dinoapp.fragmentosRegistro

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.example.dinoapp.InterfaceTransferencia
import com.example.dinoapp.R
import com.example.dinoapp.databinding.FragmentNombreBinding

class NombreFragment : Fragment() {

    private var _binding : FragmentNombreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myInterface : InterfaceTransferencia = activity as InterfaceTransferencia
        _binding = FragmentNombreBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.buttonContinue.isEnabled = false
        binding.buttonContinue.isClickable = false

        binding.inputEdit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.inputEdit.text.toString().isNotEmpty()){
                    binding.buttonContinue.isEnabled = true
                    binding.buttonContinue.isClickable = true
                }else{
                    binding.buttonContinue.isEnabled = false
                    binding.buttonContinue.isClickable = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.buttonContinue.setOnClickListener {
            myInterface.transferirNombre(binding.inputEdit.text.toString())

            myInterface.continuar()
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}