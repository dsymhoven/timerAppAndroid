package com.example.dsymhove.timerappandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private enum class TimerState {
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerState = TimerState.Stopped
    private var secondsRemaining = 5L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timerTextView = findViewById<TextView>(R.id.timeTextView)
        val startStopButton = findViewById<Button>(R.id.startStopButton)

        startStopButton.setOnClickListener {v ->
            Log.d("onClick", "Got here")
            if (startStopButton.text == getString(R.string.start)) {
                startStopButton.setText(R.string.stop)
                val startTime = System.currentTimeMillis()
                startTimer()
            } else {
                startStopButton.setText(R.string.start)
            }
        }
    }

    private fun startTimer() {
        timerState = TimerState.Running
        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() {
                Log.d("onFinish", "onFinish")
            }

            override fun onTick(millisUntilFinished: Long) {
                Log.d("tick", "tack")
            }
        }.start()
    }
}




