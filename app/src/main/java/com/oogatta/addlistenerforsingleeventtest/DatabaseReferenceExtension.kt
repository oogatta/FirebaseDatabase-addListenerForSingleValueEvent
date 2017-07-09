package com.oogatta.addlistenerforsingleeventtest

import com.google.firebase.database.*

private class Listener(val query: Query, val listener: ValueEventListener ): ValueEventListener {
    override fun onDataChange(dataSnapshot: DataSnapshot) {
        query.removeEventListener(this)
        listener.onDataChange(dataSnapshot)
    }

    override fun onCancelled(databaseError: DatabaseError) {
        listener.onCancelled(databaseError)
    }
}

fun DatabaseReference.addSingleValueEventListener(listener: ValueEventListener): ValueEventListener {
    return addValueEventListener(Listener(this, listener))
}