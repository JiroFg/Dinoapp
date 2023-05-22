package com.example.dinoapp.fragment

import android.app.Dialog
import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.BaseColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.dinoapp.DataBaseSQLite.DbContract
import com.example.dinoapp.DataBaseSQLite.DinoItem
import com.example.dinoapp.DataBaseSQLite.dbHelper
import com.example.dinoapp.DinoRecycler.Dino
import com.example.dinoapp.HomeActivity
import com.example.dinoapp.MainActivity.Companion.prefs
import com.example.dinoapp.R
import com.example.dinoapp.ShopRecycler.ShopAdapter
import com.example.dinoapp.databinding.FragmentFShopBinding

class FShop : Fragment() {

    private var _binding: FragmentFShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ShopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFShopBinding.inflate(inflater, container, false)
        //metodo para colocar la información del usuario en la TopBar
        addInformationUser()
        //Listener cuando presionas el botón para girar la ruleta
        binding.btnRuleta.setOnClickListener {
            //Genera un valor random entre 1 y 1000
            val randomNumber = (1..1000).random()
            //En un When dependiendo el valor obtenido da un premio u otro
            when (randomNumber) {
                in 1..600 -> {
                    Toast.makeText(context, "Comun", Toast.LENGTH_SHORT).show()
                    showDialogPremio1()
                }

                in 601..800 -> {
                    Toast.makeText(context, "No común", Toast.LENGTH_SHORT).show()
                    showDialogPremio2()
                }

                in 801..949 -> {
                    Toast.makeText(context, "Raro", Toast.LENGTH_SHORT).show()
                    showDialogPremio3()
                }

                in 950..1000 -> {
                    Toast.makeText(context, "Legendario", Toast.LENGTH_SHORT).show()
                    showDialogPremio4()
                }
            }
        }
        initRecyclerView()
        return binding.root
    }
    //metodo para inicializar el RecyclerView que dibuja los dinosaurios disponibles en la tienda
    private fun initRecyclerView() {
        val recyclerView = binding.recyclerShop
        //crea un grid layout de dos columnas donde se colocaran los items
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        adapter = ShopAdapter(HomeActivity.dinoData) { onItemSelected(it) }
        recyclerView.adapter = adapter
    }

    //Metodo cuando un elemento del recycler view de la tienda es seleccionado
    private fun onItemSelected(dinoItem: Dino) {
        val dialog = Dialog(requireActivity())
        dialog.setContentView(R.layout.dialog_purchase)
        val dialogText: TextView = dialog.findViewById(R.id.nombre)
        val dialogImg: ImageView = dialog.findViewById(R.id.premio)
        val dialogBtn: Button = dialog.findViewById(R.id.dino_comprar)
        val dialogBtn_Cancelar: Button = dialog.findViewById(R.id.dialog_aceptar)
        //asigna el nombre del dinosaurio al dialog
        dialogText.text = dinoItem.nombre
        //asigna la imagen del dinosaurio al dialog
        Glide.with(requireActivity())
            .load(dinoItem.img)
            .into(dialogImg)
        //listener del boton si acepta la compra
        dialogBtn.setOnClickListener {
            comprarDino(dinoItem)
            dialog.dismiss()
        }
        //listener del boton si cancela la compra
        dialogBtn_Cancelar.setOnClickListener {
            dialog.dismiss()
        }
        //muestra el dialog
        dialog.show()
    }

    private fun comprarDino(dinoItem: Dino) {
        //verifica que el dinosaurio este o no este comprado ya, los elementos comprados se encuentran en la base de datos y en el array shopData
        val dino = HomeActivity.shopData.filter { it.DinoID == dinoItem.id }
        //Si no esta comprado y por ende la lista esta vacia entonces procede a comprarse
        if (dino.isEmpty()) {
            val dbHelper = dbHelper(requireContext()).writableDatabase
            val values = ContentValues().apply {
                put(DbContract.FeedEntry.COLUMN_NAME_DINO_ID, dinoItem.id)
            }
            val idDinoDb = dbHelper.insert(DbContract.FeedEntry.TABLE_NAME, null, values)
            Toast.makeText(context, "Se agrego el dino: $idDinoDb", Toast.LENGTH_SHORT).show()
            //metodo para actualizar el array de referencia
            recargarShop()
            adapter.reloadAdapter()
        } else {
            Toast.makeText(context, "Dinosaurio ya comprado", Toast.LENGTH_SHORT).show()
        }
    }

    //metodo para actualizar el array de referencia de los dinosaurios comprados
    private fun recargarShop() {
        //limpia el antiguo array
        HomeActivity.shopData.clear()
        val dbHelper = dbHelper(requireContext()).readableDatabase
        val cursor = dbHelper.query(
            DbContract.FeedEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        with(cursor) {
            while (moveToNext()) {
                val item = DinoItem(getInt(getColumnIndexOrThrow(BaseColumns._ID)), getInt(getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_DINO_ID)))
                HomeActivity.shopData.add(item)
            }
        }
    }

    private fun showDialogPremio1() {
        val dialog = Dialog(requireContext())
//        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE )
//        dialog.setCancelable( true )
        dialog.setContentView(R.layout.dialog_premio1)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogButton: Button = dialog.findViewById(R.id.dialog_aceptar)
        dialogButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun showDialogPremio2() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable( true )
        dialog.setContentView(R.layout.dialog_premio2)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogButton: Button = dialog.findViewById(R.id.dialog_aceptar)
        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDialogPremio3() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable( true )
        dialog.setContentView(R.layout.dialog_premio3)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogButton: Button = dialog.findViewById(R.id.dialog_aceptar)
        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDialogPremio4() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable( true )
        dialog.setContentView(R.layout.dialog_premio4)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogButton: Button = dialog.findViewById(R.id.dialog_aceptar)
        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun addInformationUser() {
        binding.name.text = prefs.getName()
    }
}
