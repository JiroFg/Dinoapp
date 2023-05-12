package com.example.dinoapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFilterFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val filterInterface: InterfaceFilters = activity as InterfaceFilters
            val list = resources.getStringArray(R.array.filter_list)
            val selectedItems = ArrayList<String>()
            val builder = AlertDialog.Builder(it,R.style.MyAlertDialogStyle)
            builder.setTitle("Filtros")
                .setMultiChoiceItems(R.array.filter_list, null
                ) { _, which, isChecked ->
                    if (isChecked) {
                        val aux = list[which].toString()
                        selectedItems.add(aux)
                    } else {
                        val aux = list[which].toString()
                        selectedItems.remove(aux)

                    }
                }
                .setPositiveButton("Aplicar"
                ) { _, _ ->
                    filterInterface.filtersTrans(selectedItems)
                    dismiss()
                }
                .setNegativeButton("Cancelar"
                ) { _, _ ->
                    dismiss()
                }
                .setNeutralButton("Borrar"
                ) { _, _ ->
                    filterInterface.cleanFilters()
                    dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}