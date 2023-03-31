package com.example.dinoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.dinoapp.databinding.ActivityHomeBinding
import com.example.dinoapp.fragment.FBook
import com.example.dinoapp.fragment.FHome
import com.example.dinoapp.fragment.FProfile
import com.example.dinoapp.fragment.FShop



class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        //SPLASH SCREEN
        installSplashScreen()

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ## Problema de internet :D

        showDialog()


        replaceFragment(FShop())

        binding.bottomNavigationView2.setOnItemReselectedListener {
            when (it.itemId) {

                R.id.nav_shop -> replaceFragment(FShop())
                R.id.nav_home -> replaceFragment(FHome())
                R.id.nav_profile -> replaceFragment(FProfile())
                R.id.nav_book -> replaceFragment(FBook())

                else -> {

                }
            }
            true
        }
    }

    private fun showDialog() {
        val view = View.inflate(this@Home, R.layout.error_wifi, null)
        val builder = AlertDialog.Builder(this@Home)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
        drawLayout()
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET))

    }

    private fun drawLayout() {
        if (isNetworkAvailable()) {
            Toast.makeText(this, "Si hay", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "No hay", Toast.LENGTH_LONG).show()
        }
    }
}
