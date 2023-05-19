package com.example.dinoapp.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
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
import com.example.dinoapp.DinoRecycler.DinoProvider
import com.example.dinoapp.R
import com.example.dinoapp.ShopRecycler.ShopAdapter
import com.example.dinoapp.ShopRecycler.ShopItem
import com.example.dinoapp.ShopRecycler.ShopProvider
import com.example.dinoapp.databinding.FragmentFShopBinding
class FShop : Fragment() {

    private var _binding: FragmentFShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ShopAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFShopBinding.inflate(inflater,container,false)
        binding.btnRuleta.setOnClickListener {
            val randomNumber = (1..1000).random()

            when(randomNumber) {
                in 1..600 -> {
                    Toast.makeText(context, "Comun", Toast.LENGTH_SHORT).show()
                    showDialogPremio1()
                }
                in 601..800 -> Toast.makeText(context, "No común", Toast.LENGTH_SHORT).show()
                in 801..949 -> Toast.makeText(context, "Raro", Toast.LENGTH_SHORT).show()
                in 950..1000 -> Toast.makeText(context, "Legendario", Toast.LENGTH_SHORT).show()
            }
        }
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView(){
        val recyclerView = binding.recyclerShop
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        adapter = ShopAdapter(ShopProvider.shopList){onItemSelected(it)}
        recyclerView.adapter = adapter
    }

    private fun onItemSelected(item: ShopItem){
        Log.d("PRUEBA","ENTRO")
        val dialog = Dialog(requireActivity())
        dialog.setContentView(R.layout.dialog_purchase)
        val dialogText: TextView = dialog.findViewById(R.id.nombre)
        val dialogImg: ImageView = dialog.findViewById(R.id.premio)
        val dialogBtn: Button = dialog.findViewById(R.id.dino_comprar)
        val dialogBtn_Cancelar: Button = dialog.findViewById(R.id.dialog_aceptar)

        val dino = DinoProvider.dinoList.filter { it.id == item.dinoID }
        dialogText.text = dino[0].nombre
        Glide.with(requireActivity())
            .load(dino[0].img)
            .into(dialogImg)
        dialogBtn.setOnClickListener {
            item.purshased = true
            dialog.dismiss()
        }
        dialogBtn_Cancelar.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDialogPremio1() {

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE )
//        dialog.setCancelable( true )
        dialog.setContentView( R.layout.dialog_premio1 )
        dialog.window?.setBackgroundDrawable( ColorDrawable( Color.TRANSPARENT ) )

        val dialogButton : Button = dialog.findViewById( R.id.dialog_aceptar )
        dialogButton.setOnClickListener {
                dialog.dismiss()
        }
    }
}