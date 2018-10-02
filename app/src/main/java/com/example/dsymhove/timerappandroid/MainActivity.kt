package com.example.dsymhove.timerappandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timerTextView = findViewById<TextView>(R.id.timeTextView)
        val startStopButton = findViewById<Button>(R.id.startStopButton)

        startStopButton.setOnClickListener {v ->
            Log.d("onClick", "Got here")
            if (startStopButton.text == getString(R.string.start)) {
                startStopButton.setText(R.string.stop)
            } else {
                startStopButton.setText(R.string.start)
            }
        }
    }
}
