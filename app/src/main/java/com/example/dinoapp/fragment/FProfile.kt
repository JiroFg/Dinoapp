package com.example.dinoapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.dinoapp.HomeActivity
import com.example.dinoapp.InterfaceConexion
import com.example.dinoapp.InterfaceTransferencia
import com.example.dinoapp.profileEdit.Change_name
import com.example.dinoapp.Prefs.Prefs
import com.example.dinoapp.databinding.FragmentFProfileBinding
import com.example.dinoapp.profileEdit.ChangeProfileImage

class FProfile : Fragment() {

    private var _binding: FragmentFProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefs: Prefs
    var profileImage = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFProfileBinding.inflate(inflater, container, false)
        prefs = Prefs(binding.name.context)
        val myInterface : InterfaceConexion = activity as InterfaceConexion
        binding.progressBar.max = HomeActivity.dinoData.size
        addInformationUser()
        addEvents(myInterface)
        return binding.root
    }
    fun addEvents(myInterfaceConexion: InterfaceConexion) {
        binding.btnEdiName.setOnClickListener {
            val intent = Intent(activity, Change_name::class.java)
            startActivity(intent)
        }

        binding.btnEditAvatar.setOnClickListener {
            if(myInterfaceConexion.verificarConexion()) {
                val intent = Intent(activity, ChangeProfileImage::class.java)
                startActivity(intent)
            }else{
                myInterfaceConexion.showError()
            }
        }
    }
    fun addInformationUser() {
        binding.name.text = prefs.getName()
        profileImage = prefs.getImg()
        Glide.with(this)
            .asBitmap()
            .load(profileImage)
            .into(binding.avatarImg)
        binding.dinos.text = HomeActivity.shopData.size.toString() + "/" + HomeActivity.dinoData.size.toString()
        binding.progressBar.progress = prefs.getLvl()
        binding.nivel.text = "LV " + prefs.getLvl().toString()
    }

    override fun onResume() {
        super.onResume()
        addInformationUser()
    }
}