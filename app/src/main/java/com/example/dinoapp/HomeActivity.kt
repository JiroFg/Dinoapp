package com.example.dinoapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.provider.BaseColumns
import android.util.Log
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.dinoapp.DataBaseSQLite.DbContract
import com.example.dinoapp.DataBaseSQLite.DinoItem
import com.example.dinoapp.DataBaseSQLite.dbHelper
import com.example.dinoapp.DinoRecycler.Dino
import com.example.dinoapp.DinoRecycler.DinoProvider
import com.example.dinoapp.LessonRecycler.Lesson
import com.example.dinoapp.LessonRecycler.LessonProvider
import com.example.dinoapp.databinding.ActivityHomeBinding
import com.example.dinoapp.fragment.FBook
import com.example.dinoapp.fragment.FHome
import com.example.dinoapp.fragment.FProfile
import com.example.dinoapp.fragment.FShop
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity(), InterfaceFilters {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var fBook: FBook
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            DinoProvider.cargarDinos()
            LessonProvider.cargarLessons()
            cargarShop()
        }

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
    }

    private fun cargarShop() {
        shopData.clear()
        val dbHelper = dbHelper(this).readableDatabase
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
                val item = DinoItem(
                    getInt(getColumnIndexOrThrow(BaseColumns._ID)),
                    getInt(getColumnIndexOrThrow(DbContract.FeedEntry.COLUMN_NAME_DINO_ID))
                )
                Log.d("PRUEBA ITEM SHOP", item.toString())
                shopData.add(item)
            }
        }
    }

    private fun showDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.error_conexion)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogButton: Button = dialog.findViewById(R.id.dialog_button)
        dialogButton.setOnClickListener {
            if (isNetworkAvailable()) {
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun replaceFragment(fragment: Fragment) {
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
        if (!isNetworkAvailable()) {
            showDialog()
        }
    }

    override fun filtersTrans(list: ArrayList<String>) {
        fBook.filterList(list)
    }

    override fun cleanFilters() {
        fBook.cleanFilters()
    }

    companion object {
        val dinoData = mutableListOf<Dino>()
        val lessonData = mutableListOf<Lesson>()
        val shopData = mutableListOf<DinoItem>()
    }
}
