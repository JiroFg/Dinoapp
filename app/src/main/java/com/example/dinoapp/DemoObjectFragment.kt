package com.example.dinoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dinoapp.databinding.FragmentDemoObjectBinding


class DemoObjectFragment : Fragment() {

    companion object {
        private const val ARG_OBJECT = "object"
    }

    private var _binding: FragmentDemoObjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDemoObjectBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            binding.textView.text = "Fragmento " + getInt(ARG_OBJECT).toString()
        }
    }
}