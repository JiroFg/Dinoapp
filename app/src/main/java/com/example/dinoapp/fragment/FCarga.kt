package com.example.dinoapp.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dinoapp.R

class FCarga : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Handler().postDelayed({
            val fhome = FHome()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, fhome)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        },2000)

        return inflater.inflate(R.layout.carga, container, false)
    }
}
