package com.example.dinoapp.Prefs

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Prefs(val context: Context) {

    val PREF_NAME = "myData"
    val SHARED_USER_GENDER = "userGender"
    val SHARED_USER_NAME = "userName"
    val SHARED_USER_IMG = "userImg"
    val SHARED_USER_LVL = "userLvl"
    val SHARED_USER_COINS = "userCoins"
    val SHARED_USER_DINOS = "userDinos"
    val PREF_SHOW_INTRO = "intro"
    val storage = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    fun saveData(gender:Boolean,name:String,img:String){
        storage.edit().putBoolean(SHARED_USER_GENDER, gender).apply()
        storage.edit().putString(SHARED_USER_NAME, name).apply()
        storage.edit().putString(SHARED_USER_IMG, img).apply()
    }

    fun editGender(gender: Boolean){
        storage.edit().putBoolean(SHARED_USER_GENDER, gender).apply()
    }

    fun editName(name:String){
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun editImg(img:String){
        storage.edit().putString(SHARED_USER_IMG, img).apply()
    }

    fun editLvl(lvl:Int){
        storage.edit().putInt(SHARED_USER_LVL,lvl).apply()
    }

    fun editCoins(coins:Int){
        storage.edit().putInt(SHARED_USER_COINS, coins).apply()
    }

    fun editDinos(dinos:MutableList<String>){
        storage.edit().putStringSet(SHARED_USER_DINOS,dinos.toSet()).apply()
    }

    fun editIntro(intro:Boolean){
        storage.edit().putBoolean(PREF_SHOW_INTRO, intro).apply()
    }
    //gets

    fun myToString():String{
        val myToString = "Gender: "+getGender()+
                " Name: "+getName()+
                " Img: "+getImg()+
                " Intro: "+getIntro()
        return myToString
    }

    fun getGender():Boolean{
        return storage.getBoolean(SHARED_USER_GENDER, true)!!
    }

    fun getName():String{
        return storage.getString(SHARED_USER_NAME,"")!!
    }

    fun getImg():String{
        return storage.getString(SHARED_USER_IMG, "")!!
    }

    fun getLvl():Int{
        return storage.getInt(SHARED_USER_LVL, 1)
    }

    fun getCoins():Int{
        return storage.getInt(SHARED_USER_COINS, 0)
    }

    fun getDinos():MutableList<String>{
        return storage.getStringSet(SHARED_USER_DINOS, setOf<String>())!!.toMutableList()
    }

    fun getIntro():Boolean{
        return storage.getBoolean(PREF_SHOW_INTRO,true)
    }
}