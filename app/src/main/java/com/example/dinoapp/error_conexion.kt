package com.example.dinoapp

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.dinoapp.databinding.DialogConexionBinding

class error_conexion(
    private val onSubmitClickListener: () -> Unit
): DialogFragment() {
    private lateinit var binding : DialogConexionBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogConexionBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.bReintentar.setOnClickListener{
            onSubmitClickListener.invoke()
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}