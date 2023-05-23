package com.example.dinoapp.Quiz

object QuizSetData{

    const val score = "score"
    fun agarrarPregunta():ArrayList<QuizPreguntaData>{
        var pregunta:ArrayList<QuizPreguntaData> = arrayListOf()

        var p1 = QuizPreguntaData(
            1,
            "Que significa dilophosaurus",
            "Ladron del alba",
            "Lagarto venenoso",
            "Lagarto de dos crestas",
            "Lagarto de una cresta",
            3
        )

        var p2 = QuizPreguntaData(
            2,
            "pregunta 2",
            "correcta",
            "incorrecta",
            "incorrecta",
            "incorrecta",
            1
        )
        var p3 = QuizPreguntaData(
            3,
            "pregunta 3",
            "incorrecta",
            "correcta",
            "incorrecta",
            "incorrecta",
            2
        )
        var p4 = QuizPreguntaData(
            10,
            "pregunta 4",
            "incorrecta",
            "incorrecta",
            "incorrecta",
            "correcta",
            4
        )
        pregunta.add(p1)
        pregunta.add(p2)
        pregunta.add(p3)
        pregunta.add(p4)
        return pregunta
    }
}
