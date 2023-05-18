package com.example.dinoapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.util.Log
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.dinoapp.DinoRecycler.Dino
import com.example.dinoapp.databinding.ActivityHomeBinding
import com.example.dinoapp.fragment.FBook
import com.example.dinoapp.fragment.FHome
import com.example.dinoapp.fragment.FProfile
import com.example.dinoapp.fragment.FShop
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeActivity : AppCompatActivity(), InterfaceFilters{

    private lateinit var binding: ActivityHomeBinding
    private lateinit var fBook: FBook
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fShop = FShop()
        val fHome = FHome()
        val fProfile = FProfile()
        fBook = FBook()

        //se asignan los valores por defecto
        replaceFragment(fHome)
        binding.bottomNavigationView2.selectedItemId = R.id.nav_home

        binding.bottomNavigationView2.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.nav_shop -> replaceFragment(fShop)
                R.id.nav_home -> replaceFragment(fHome)
                R.id.nav_profile -> replaceFragment(fProfile)
                R.id.nav_book -> replaceFragment(fBook)
            }
            true
        }

        prueba()

    }

    private fun prueba(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://dinoapi-bior.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create<APIService>(APIService::class.java)
        service.getDinos().enqueue(object : Callback<List<Dino>>{
            override fun onResponse(call: Call<List<Dino>>, response: Response<List<Dino>>) {
                val dinos = response.body()
                dinos?.forEach{
                    Log.d("PRUEBA", it.toString())
                }
            }

            override fun onFailure(call: Call<List<Dino>>, t: Throwable) {
                t?.printStackTrace()
            }

        })
    }

    private fun showDialog() {

        val dialog = Dialog( this )
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE )
        dialog.setCancelable( false )
        dialog.setContentView( R.layout.error_conexion )
        dialog.window?.setBackgroundDrawable( ColorDrawable( Color.TRANSPARENT ) )

        val dialogButton : Button = dialog.findViewById( R.id.dialog_button )
        dialogButton.setOnClickListener {
            if( isNetworkAvailable() ) {
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
        drawLayout()
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET))

    }

    private fun drawLayout() {
        if ( !isNetworkAvailable() ) {
            showDialog()
        }
    }

    override fun filtersTrans(list: ArrayList<String>) {
        fBook.filterList(list)
    }

    override fun cleanFilters() {
        fBook.cleanFilters()
    }
}
