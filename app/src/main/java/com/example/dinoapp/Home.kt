package com.example.dinoapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.view.Window
import android.widget.Button
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

        val dialog = Dialog( this );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setCancelable( false );
        dialog.setContentView( R.layout.error_conexion );
        dialog.window?.setBackgroundDrawable( ColorDrawable( Color.TRANSPARENT ) );

        val dialogButton : Button = dialog.findViewById( R.id.dialog_button );
        dialogButton.setOnClickListener {
            dialog.dismiss();
        }
        dialog.show();
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
        if ( !isNetworkAvailable() ) {
            showDialog();
        }
    }
}
