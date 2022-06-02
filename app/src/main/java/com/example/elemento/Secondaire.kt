package com.example.elemento

class Secondaire(val nom: String, val degats: Float) {
    val timer: Int = 3
    var tempsRestant: Int = 1

    fun AttaqueMonto(monstre: Monstre, type: String){
        var deg: Float
        if(type == "feu" && monstre.type == "plante"){
            deg = degats*1.5f
        }
        else if(type == "feu" && monstre.type == "eau"){
            deg = degats*0.8f
        }
        else if(type == "eau" && monstre.type == "feu"){
            deg = degats*1.5f
        }
        else if(type == "eau" && monstre.type == "plante"){
            deg = degats*0.8f
        }
        else if(type == "plante" && monstre.type == "eau"){
            deg = degats*1.5f
        }
        else if(type == "plante" && monstre.type == "feu"){
            deg = degats*0.8f
        }
        else{
            deg = degats
        }
        monstre.pv = monstre.pv - deg
    }


    fun AttaqueMonstre(monto: Monto, type: String){
        var deg: Float
        if(type == "feu" && monto.type == "plante"){
            deg = degats*1.5f
        }
        else if(type == "feu" && monto.type == "eau"){
            deg = degats*0.8f
        }
        else if(type == "eau" && monto.type == "feu"){
            deg = degats*1.5f
        }
        else if(type == "eau" && monto.type == "plante"){
            deg = degats*0.8f
        }
        else if(type == "plante" && monto.type == "eau"){
            deg = degats*1.5f
        }
        else if(type == "plante" && monto.type == "feu"){
            deg = degats*0.8f
        }
        else{
            deg = degats
        }
        monto.pv = monto.pv - deg
    }
}