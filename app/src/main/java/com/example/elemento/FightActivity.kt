package com.example.elemento

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.*

class FightActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fight)

        var tour: Int = 0

        val montos = ArrayList<Monto>()
        val dejaJoue = ArrayList<Monto>()
        val vaincus = ArrayList<Monto>()


        //liste des créatures
        val monto = Monto("Flammeche","feu",20f,20f, 5f)
        val monto1 = Monto("Vaguelette","eau",20f,20f, 5f)
        var montoActuel = Monto("","",0f,0f,0f)
        val monstre = Monstre("plante", 100f,100f, 8f)

        monto.seconde = Secondaire("Colonne de flammes",8f)
        monto1.seconde = Secondaire("Raz de marée",8f)
        monstre.seconde = Secondaire("Cage de racines",8f)

        val TxtPV = findViewById<TextView>(R.id.pv)
        val TxtDeg = findViewById<TextView>(R.id.dégats)
        val TxtType = findViewById<TextView>(R.id.type)
        val TxtNom = findViewById<TextView>(R.id.nom)

        val TxtPVBoss = findViewById<TextView>(R.id.pvBoss)
        val TxtNomBoss = findViewById<TextView>(R.id.nomBoss)
        val TxtTypeBoss = findViewById<TextView>(R.id.typeBoss)

        val imageMonto = findViewById<LinearLayout>(R.id.imageMonto)
        val monstreIm = findViewById<ImageView>(R.id.monstre)

        montos.add(monto)
        montos.add(monto1)

        val btnAtt1 = findViewById<Button>(R.id.att1) as Button
        val btnAtt2 = findViewById<Button>(R.id.att2) as Button
        val suivant = findViewById<ImageButton>(R.id.suivant) as ImageButton
        val précédent = findViewById<ImageButton>(R.id.précédent) as ImageButton

        val intent = Intent(this, MainActivity::class.java)


        TxtPVBoss.text = "PV: "+monstre.pv
        TxtNomBoss.text ="Nom: BOSS1"
        TxtTypeBoss.text = "Type: "+monstre.type


        montoActuel = montos.get(tour)

        TxtNom.text = montoActuel.nom
        TxtPV.text = "PV: "+montoActuel.pv
        TxtDeg.text = "Degats: "+montoActuel.attBase
        TxtType.text = "Type: "+montoActuel.type
        btnAtt2.text = montoActuel.seconde.nom

        if(montoActuel.nom == "Flammeche"){
            imageMonto.setBackgroundResource(R.drawable.monstre_r)
        }
        else if(montoActuel.nom == "Vaguelette"){
            imageMonto.setBackgroundResource(R.drawable.monstre_b)
        }

        if(montoActuel.type == "feu"){
            btnAtt1.text = "Boule de feu"
        }
        if(montoActuel.type == "eau"){
            btnAtt1.text = "Jet d'eau"
        }
        if(montoActuel.type == "plante"){
            btnAtt1.text = "Coup de lianes"
        }

        btnAtt1.setOnClickListener() {
            montoActuel.Attaque(monstre)
            if(montoActuel.seconde.tempsRestant > 0){
                montoActuel.seconde.tempsRestant--
            }
            if(monstre.estVivant() == false) {
                TxtPVBoss.text = "PV: 0"
                monstreIm.setImageDrawable(null)
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Le monstre a été vaincu")
                builder.setPositiveButton("Ok") { dialogInterface: DialogInterface?, i: Int ->
                    startActivity(intent)
                }
                builder.show()
            }
            else {
                TxtPVBoss.text = "PV: " + monstre.pv
            }

            if(montos.size == 1){
                var i: Int = 0
                val j: Int = dejaJoue.size
                monstre.Attaque(montos.get(0))
                if(montos.get(0).estVivant() == false){
                    vaincus.add(montos.get(0))
                    montos.remove(montos.get(0))
                    if(dejaJoue.size == 0 && montos.size == 0){
                        TxtPV.text = "PV: 0"
                        val builder = AlertDialog.Builder(this)
                        builder.setMessage("Vous avez été vaincu")
                        builder.setPositiveButton("Ok") { dialogInterface: DialogInterface?, i: Int ->
                            startActivity(intent)
                        }
                        builder.show()
                    }
                    else{
                        Toast.makeText(this,montoActuel.nom+" est mort", Toast.LENGTH_SHORT).show()
                        while(i < j){
                            montos.add(dejaJoue.get(i))
                            dejaJoue.remove(dejaJoue.get(i))
                            i++
                        }
                        if(tour == montos.size-1 || tour == montos.size){
                            tour = 0
                        }
                        else{
                            tour++
                        }
                        montoActuel = montos.get(tour)
                        TxtNom.text = montoActuel.nom
                        TxtPV.text = "PV: "+montoActuel.pv
                        TxtDeg.text = "Degats: "+montoActuel.attBase
                        TxtType.text = "Type: "+montoActuel.type
                        btnAtt2.text = montoActuel.seconde.nom
                        if(montoActuel.nom == "Flammeche"){
                            imageMonto.setBackgroundResource(R.drawable.monstre_r)
                        }
                        else if(montoActuel.nom == "Vaguelette"){
                            imageMonto.setBackgroundResource(R.drawable.monstre_b)
                        }
                        if(montoActuel.type == "feu"){
                            btnAtt1.text = "Boule de feu"
                        }
                        if(montoActuel.type == "eau"){
                            btnAtt1.text = "Jet d'eau"
                        }
                        if(montoActuel.type == "plante"){
                            btnAtt1.text = "Coup de lianes"
                        }
                    }
                }
                else{
                    TxtPV.text = "PV: "+montos.get(0).pv
                    while(i < j){
                        montos.add(i,dejaJoue.get(i))
                        dejaJoue.remove(dejaJoue.get(i))
                        i++
                    }
                    tour = 0
                    montoActuel = montos.get(tour)
                    TxtNom.text = montoActuel.nom
                    TxtPV.text = "PV: "+montoActuel.pv
                    TxtDeg.text = "Degats: "+montoActuel.attBase
                    TxtType.text = "Type: "+montoActuel.type
                    btnAtt2.text = montoActuel.seconde.nom
                    if(montoActuel.nom == "Flammeche"){
                        imageMonto.setBackgroundResource(R.drawable.monstre_r)
                    }
                    else if(montoActuel.nom == "Vaguelette"){
                        imageMonto.setBackgroundResource(R.drawable.monstre_b)
                    }
                    if(montoActuel.type == "feu"){
                        btnAtt1.text = "Boule de feu"
                    }
                    if(montoActuel.type == "eau"){
                        btnAtt1.text = "Jet d'eau"
                    }
                    if(montoActuel.type == "plante"){
                        btnAtt1.text = "Coup de lianes"
                    }
                }
            }
            else{
                dejaJoue.add(montos.get(tour))
                montos.remove(montos.get(tour))
                if(tour == montos.size-1 || tour == montos.size){
                    tour = 0
                }
                else{
                    tour++
                }
                montoActuel = montos.get(tour)
                TxtNom.text = montoActuel.nom
                TxtPV.text = "PV: "+montoActuel.pv
                TxtDeg.text = "Degats: "+montoActuel.attBase
                TxtType.text = "Type: "+montoActuel.type
                btnAtt2.text = montoActuel.seconde.nom
                if(montoActuel.nom == "Flammeche"){
                    imageMonto.setBackgroundResource(R.drawable.monstre_r)
                }
                else if(montoActuel.nom == "Vaguelette"){
                    imageMonto.setBackgroundResource(R.drawable.monstre_b)
                }
                if(montoActuel.type == "feu"){
                    btnAtt1.text = "Boule de feu"
                }
                if(montoActuel.type == "eau"){
                    btnAtt1.text = "Jet d'eau"
                }
                if(montoActuel.type == "plante"){
                    btnAtt1.text = "Coup de lianes"
                }
            }
        }

        btnAtt2.setOnClickListener(){
            if(montoActuel.seconde.tempsRestant == 0) {
                montoActuel.seconde.AttaqueMonto(monstre, montoActuel.type)
                montoActuel.seconde.tempsRestant = montoActuel.seconde.timer
                if (monstre.estVivant() == false) {
                    TxtPVBoss.text = "PV: 0"
                    monstreIm.setImageDrawable(null)
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("Le monstre a été vaincu")
                    builder.setPositiveButton("Ok") { dialogInterface: DialogInterface?, i: Int ->
                        startActivity(intent)
                    }
                    builder.show()
                } else {
                    TxtPVBoss.text = "PV: " + monstre.pv
                }

                if (montos.size == 1) {
                    var i: Int = 0
                    val j: Int = dejaJoue.size
                    monstre.Attaque(montos.get(0))
                    while (i < j) {
                        montos.add(dejaJoue.get(i))
                        dejaJoue.remove(dejaJoue.get(i))
                        i++
                    }
                    if (montos.get(0).estVivant() == false) {
                        vaincus.add(montos.get(0))
                        montos.remove(montos.get(0))
                        if (dejaJoue.size == 0 && montos.size == 0) {
                            TxtPV.text = "PV: 0"
                            val builder = AlertDialog.Builder(this)
                            builder.setMessage("Vous avez été vaincu")
                            builder.setPositiveButton("Ok") { dialogInterface: DialogInterface?, i: Int ->
                                startActivity(intent)
                            }
                            builder.show()
                        } else {
                            Toast.makeText(this,montoActuel.nom+" est mort", Toast.LENGTH_SHORT).show()
                            if (tour == montos.size - 1 || tour == montos.size) {
                                tour = 0
                            } else {
                                tour++
                            }
                            montoActuel = montos.get(tour)
                            TxtNom.text = montoActuel.nom
                            TxtPV.text = "PV: " + montoActuel.pv
                            TxtDeg.text = "Degats: " + montoActuel.attBase
                            TxtType.text = "Type: " + montoActuel.type
                            btnAtt2.text = montoActuel.seconde.nom
                            if (montoActuel.nom == "Flammeche") {
                                imageMonto.setBackgroundResource(R.drawable.monstre_r)
                            } else if (montoActuel.nom == "Vaguelette") {
                                imageMonto.setBackgroundResource(R.drawable.monstre_b)
                            }
                            if (montoActuel.type == "feu") {
                                btnAtt1.text = "Boule de feu"
                            }
                            if (montoActuel.type == "eau") {
                                btnAtt1.text = "Jet d'eau"
                            }
                            if (montoActuel.type == "plante") {
                                btnAtt1.text = "Coup de lianes"
                            }
                        }
                    } else {
                        TxtPV.text = "PV: " + montos.get(0).pv
                    }
                } else {
                    dejaJoue.add(montos.get(tour))
                    montos.remove(montos.get(tour))
                    if (tour == montos.size - 1 || tour == montos.size) {
                        tour = 0
                    } else {
                        tour++
                    }
                    montoActuel = montos.get(tour)
                    TxtNom.text = montoActuel.nom
                    TxtPV.text = "PV: " + montoActuel.pv
                    TxtDeg.text = "Degats: " + montoActuel.attBase
                    TxtType.text = "Type: " + montoActuel.type
                    btnAtt2.text = montoActuel.seconde.nom
                    if (montoActuel.nom == "Flammeche") {
                        imageMonto.setBackgroundResource(R.drawable.monstre_r)
                    } else if (montoActuel.nom == "Vaguelette") {
                        imageMonto.setBackgroundResource(R.drawable.monstre_b)
                    }
                    if (montoActuel.type == "feu") {
                        btnAtt1.text = "Boule de feu"
                    }
                    if (montoActuel.type == "eau") {
                        btnAtt1.text = "Jet d'eau"
                    }
                    if (montoActuel.type == "plante") {
                        btnAtt1.text = "Coup de lianes"
                    }
                }
            }
            else{
                Toast.makeText(this,"Vous ne pouvez pas utiliser cette attaque\n"+montoActuel.seconde.tempsRestant+" tour(s) restant(s)", Toast.LENGTH_SHORT).show()
            }

        }

        suivant.setOnClickListener(){
            if(tour == montos.size-1){
                tour = 0
            }
            else{
                tour++
            }
            montoActuel = montos.get(tour)
            TxtNom.text = montoActuel.nom
            TxtPV.text = "PV: "+montoActuel.pv
            TxtDeg.text = "Degats: "+montoActuel.attBase
            TxtType.text = "Type: "+montoActuel.type
            btnAtt2.text = montoActuel.seconde.nom
            if(montoActuel.nom == "Flammeche"){
                imageMonto.setBackgroundResource(R.drawable.monstre_r)
            }
            else if(montoActuel.nom == "Vaguelette"){
                imageMonto.setBackgroundResource(R.drawable.monstre_b)
            }
            if(montoActuel.type == "feu"){
                btnAtt1.text = "Boule de feu"
            }
            if(montoActuel.type == "eau"){
                btnAtt1.text = "Jet d'eau"
            }
            if(montoActuel.type == "plante"){
                btnAtt1.text = "Coup de lianes"
            }
        }
        précédent.setOnClickListener(){
            if(tour == 0){
                tour = montos.size-1
            }
            else{
                tour--
            }
            montoActuel = montos.get(tour)
            TxtNom.text = montoActuel.nom
            TxtPV.text = "PV: "+montoActuel.pv
            TxtDeg.text = "Degats: "+montoActuel.attBase
            TxtType.text = "Type: "+montoActuel.type
            btnAtt2.text = montoActuel.seconde.nom
            if(montoActuel.nom == "Flammeche"){
                imageMonto.setBackgroundResource(R.drawable.monstre_r)
            }
            else if(montoActuel.nom == "Vaguelette"){
                imageMonto.setBackgroundResource(R.drawable.monstre_b)
            }
            if(montoActuel.type == "feu"){
                btnAtt1.text = "Boule de feu"
            }
            if(montoActuel.type == "eau"){
                btnAtt1.text = "Jet d'eau"
            }
            if(montoActuel.type == "plante"){
                btnAtt1.text = "Coup de lianes"
            }
        }




    }

}