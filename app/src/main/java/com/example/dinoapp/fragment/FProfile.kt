package com.example.dinoapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dinoapp.Change_name
import com.example.dinoapp.HomeActivity
import com.example.dinoapp.MainActivity.Companion.prefs
import com.example.dinoapp.databinding.FragmentFProfileBinding

class FProfile : Fragment() {

    private var _binding: FragmentFProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Toast.makeText(activity, prefs.getName(), Toast.LENGTH_LONG).show()
    */
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFProfileBinding.inflate(inflater, container, false)
        addInformationUser()
        addEvents()
        return binding.root
    }
    fun addEvents() {
        binding.btnEdiName.setOnClickListener {
            val intent = Intent(activity, Change_name::class.java)
            startActivity(intent)
        }
    }
    fun addInformationUser() {
        binding.name.text = prefs.getName()
    }
}