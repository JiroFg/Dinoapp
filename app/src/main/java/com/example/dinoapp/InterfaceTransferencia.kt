package com.example.dinoapp

interface InterfaceTransferencia {
    fun transferirSexo(sexo: Boolean)
    fun transferirNombre(nombre: String)
    fun transferirImg(img: String)
    fun continuar()
    fun guardarPrefs()
}