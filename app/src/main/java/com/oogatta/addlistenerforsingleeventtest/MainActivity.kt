package com.oogatta.addlistenerforsingleeventtest

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var testRef: DatabaseReference
    lateinit var valueEventListener: ValueEventListener
    lateinit var testValueEventListener: TestValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        println("MainActivity onCreate")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        testRef = FirebaseDatabase.getInstance().reference.child("testcdd")
    }

    class TestValueEventListener(val activity: Activity) : ValueEventListener {
        override fun onCancelled(error: DatabaseError?) {
            println("onCancelled")
        }

        override fun onDataChange(dataSnapshot: DataSnapshot?) {
            println("onDataChange")
            activity.title = "No!"
        }
    }

    override fun onStart() {
        println("MainActivity onStart")
        super.onStart()

        button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            val intent = Intent(this, Main2Activity::class.java)
            println("startActivity!")
            startActivity(intent)

            valueEventListener = testRef.addSingleValueEventListener(TestValueEventListener(this))
        }
    }

    override fun onStop() {
        println("MainActivity onStop")
        super.onStop()

        testRef.removeEventListener(valueEventListener)
    }

    override fun onDestroy() {
        println("MainActivity onDestroy")
        super.onDestroy()

        button.setOnClickListener(null)
    }
}
