package com.example.elemento

open class Monstre(val type: String, val pvMax: Float,var pv: Float, var attBase: Float){
    var seconde :Secondaire = Secondaire("", 0f)

    fun Attaque(monto: Monto){
        var deg: Float
        if(type == "feu" && monto.type == "plante"){
            deg = attBase*1.5f
        }
        else if(type == "feu" && monto.type == "eau"){
            deg = attBase*0.8f
        }
        else if(type == "eau" && monto.type == "feu"){
            deg = attBase*1.5f
        }
        else if(type == "eau" && monto.type == "plante"){
            deg = attBase*0.8f
        }
        else if(type == "plante" && monto.type == "eau"){
            deg = attBase*1.5f
        }
        else if(type == "plante" && monto.type == "feu"){
            deg = attBase*0.8f
        }
        else{
            deg = attBase
        }
        monto.pv = monto.pv - deg
    }

    fun estVivant():Boolean {
        var vie: Boolean = true
        if (pv <= 0) {
            vie = false
        }
        return vie
    }
}