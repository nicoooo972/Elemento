package com.example.elemento

class Monto(val nom: String,val type: String, val pvMax: Float,var pv: Float, var attBase: Float){
    var seconde :Secondaire = Secondaire("", 0f)

    fun Attaque(monstre: Monstre){
        var deg: Float
        if(type == "feu" && monstre.type == "plante"){
            deg = attBase*1.5f
        }
        else if(type == "feu" && monstre.type == "eau"){
            deg = attBase*0.8f
        }
        else if(type == "eau" && monstre.type == "feu"){
            deg = attBase*1.5f
        }
        else if(type == "eau" && monstre.type == "plante"){
            deg = attBase*0.8f
        }
        else if(type == "plante" && monstre.type == "eau"){
            deg = attBase*1.5f
        }
        else if(type == "plante" && monstre.type == "feu"){
            deg = attBase*0.8f
        }
        else{
            deg = attBase
        }
        monstre.pv = monstre.pv - deg
    }
    fun estVivant():Boolean {
        var vie: Boolean = true
        if (pv <= 0) {
            vie = false
        }
        return vie
    }
}