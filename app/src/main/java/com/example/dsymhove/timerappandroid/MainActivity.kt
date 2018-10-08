package com.example.dsymhove.timerappandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private enum class TimerState {
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private lateinit var timerTextView: TextView
    private var timerState = TimerState.Stopped
    private var secondsRemaining = 5L
    private var displayValue: Long
    get() {
        return displayValue
    }
    set(value) {
        timerTextView.text = value.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerTextView = findViewById<TextView>(R.id.timeTextView)
        val startStopButton = findViewById<Button>(R.id.startStopButton)

        setupUI()

        startStopButton.setOnClickListener {v ->
            Log.d("onClick", "Got here")
            if (startStopButton.text == getString(R.string.start)) {
                startStopButton.setText(R.string.stop)
                val startTime = System.currentTimeMillis()
                startTimer()
            } else {
                stopTimer()
            }
        }
    }

    private fun stopTimer() {
        startStopButton.setText(R.string.start)
    }

    private fun setupUI() {
        displayValue = secondsRemaining
    }

    private fun startTimer() {
        timerState = TimerState.Running
        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() {
                Log.d("onFinish", "onFinish")
                stopTimer()
                setupUI()
            }

            override fun onTick(millisUntilFinished: Long) {
                Log.d("tick", "tack")
                displayValue = (millisUntilFinished) / 1000
            }
        }.start()
    }
}




