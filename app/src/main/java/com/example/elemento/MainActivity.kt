package com.example.elemento

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {

    lateinit var chap1: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chap1 = findViewById(R.id.chap1)


        chap1.setOnClickListener(){

            val intent = Intent(this, FightActivity::class.java)
            startActivity(intent)
        }




    }


}