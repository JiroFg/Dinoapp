package com.example.dinoapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dinoapp.fragmentosRegistro.BienvenidaFragment
import com.example.dinoapp.fragmentosRegistro.ImagenFragment
import com.example.dinoapp.fragmentosRegistro.NombreFragment
import com.example.dinoapp.fragmentosRegistro.SexoFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        var getFragment : Fragment? = null
        when(position){
            0->getFragment = BienvenidaFragment()
            1->getFragment = SexoFragment()
            2->getFragment = NombreFragment()
            3->getFragment = ImagenFragment()
        }
        return getFragment!!
    }

}