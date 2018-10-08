package com.example.dsymhove.timerappandroid

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private enum class TimerState {
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private lateinit var timerTextView: TextView
    private lateinit var textToSpeech: TextToSpeech
    private var timerState = TimerState.Stopped
    private var secondsRemaining = 5L
    private var displayValue: Long
    get() = this.secondsRemaining
    set(value) {
        timerTextView.text = value.toString()
        secondsRemaining = value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textToSpeech = TextToSpeech(this, this)
        timerTextView = findViewById(R.id.timeTextView)
        val startStopButton = findViewById<Button>(R.id.startStopButton)

        setupUI()

        startStopButton.setOnClickListener {
            Log.d("onClick", "Got here")
            if (startStopButton.text == getString(R.string.start)) {
                startStopButton.setText(R.string.stop)
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
        numberPicker.wrapSelectorWheel = true
        numberPicker.maxValue = 6
        numberPicker.displayedValues = arrayOf("30", "45", "60", "90", "120", "150", "180")

        numberPicker.setOnValueChangedListener { picker, _, newVal ->
            displayValue = picker.displayedValues[newVal].toLong()
        }
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

                if(displayValue == 15L) {
                    textToSpeech.speak(displayValue.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }
        }.start()
    }

    override fun onInit(status: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}




