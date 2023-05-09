package com.example.dinoapp.DinoRecycler

class DinoProvider {
    companion object{
        val dinoList = listOf<Dino>(
            Dino(1,"Tiranousario rex","Carnivoro","Cretacico","Terrestre","Saurisquios", "Tiranosauridos",7f,12f,"https://www.sideshow.com/storage/product-images/903202/t-rex__square.jpg"),
            Dino(2,"Velociraptor","Carnivoro","Jurasico","Terrestre","Saurisquios", "Dromeosauridos",0.02f, 1.8f,"https://www.todayifoundout.com/wp-content/uploads/2010/07/Velociraptor_dinoguy2-e1278512800396.jpg"),
            Dino(3,"Triceratops","Herbivoro","Cretacico","Terrestre","Ornitisquios", "Ceratopsidos",6f,8f,"https://static01.nyt.com/images/2021/01/08/science/08TB-DINOSEED1/08TB-DINOSEED1-mediumSquareAt3X.jpg"),
            Dino(4,"Estegosaurio","Herbivoro","Jurasico","Terrestre","Ornitisquios", "Estegosauridos",6f,9f,"https://i.natgeofe.com/k/979e0635-1731-411e-b26d-ff413f9b60be/stegosaurus-side_square.jpg"),
        )
    }
}