package com.example.KittyClicker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import java.util.*

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "PET THIS CAT", Toast.LENGTH_LONG).show()
        Log.d("MainActivity", " Receiver: " + Date().toString())
    }
}