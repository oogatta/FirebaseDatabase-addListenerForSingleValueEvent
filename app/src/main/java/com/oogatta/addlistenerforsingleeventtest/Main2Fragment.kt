package com.oogatta.addlistenerforsingleeventtest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*

class Main2Fragment : Fragment() {
    lateinit var testRef: DatabaseReference
    lateinit var valueEventListener: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        testRef = FirebaseDatabase.getInstance().reference.child("testd")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main2, container, false)
    }

    class TestValueEventListener(val fragment: Fragment): ValueEventListener {
        override fun onCancelled(error: DatabaseError?) {
            println("onCancelled")
        }

        override fun onDataChange(dataSnapshot: DataSnapshot?) {
            println("onDataChange")

            // Crash!
            fragment.activity.title = "No!"
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        valueEventListener = testRef.addSingleValueEventListener(TestValueEventListener(this))
    }

    override fun onStart() {
        super.onStart()

        (activity as Main2Activity).changeMe()
    }

    override fun onStop() {
        super.onStop()

        testRef.removeEventListener(valueEventListener)
    }
}

