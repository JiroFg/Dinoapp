package com.example.dinoapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dinoapp.profileEdit.Change_name
import com.example.dinoapp.Prefs.Prefs
import com.example.dinoapp.databinding.FragmentFProfileBinding
import com.example.dinoapp.profileEdit.ChangeProfileImage

class FProfile : Fragment() {

    private var _binding: FragmentFProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefs: Prefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFProfileBinding.inflate(inflater, container, false)
        prefs = Prefs(binding.name.context)
        addInformationUser()
        addEvents()
        return binding.root
    }
    fun addEvents() {
        binding.btnEdiName.setOnClickListener {
            val intent = Intent(activity, Change_name::class.java)
            startActivity(intent)
        }

        binding.btnEditAvatar.setOnClickListener {
            val intent = Intent(activity, ChangeProfileImage::class.java)
            startActivity(intent)
        }
    }
    fun addInformationUser() {
        binding.name.text = prefs.getName()
    }
}