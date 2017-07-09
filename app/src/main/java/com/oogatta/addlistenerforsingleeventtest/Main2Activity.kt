package com.oogatta.addlistenerforsingleeventtest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        println("Main2Activity onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment, Main2Fragment())
        fragmentTransaction.commit()
    }

    override fun onDestroy() {
        println("Main2Activity onDestroy")
        super.onDestroy()
    }

    fun changeMe() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
