package com.example.dinoapp.Quiz

data class QuizPreguntaData (
    var id:Int,
    var pregunta:String,
    var opcion_uno:String,
    var opcion_dos:String,
    var opcion_tres:String,
    var opcion_cuatro:String,
    var correcta:Int
        )