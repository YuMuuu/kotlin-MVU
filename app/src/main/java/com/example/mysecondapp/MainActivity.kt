package com.example.mysecondapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

typealias  Model = Int

sealed class Msg {
    object Increment : Msg()
    object Decrement : Msg()
}

typealias update = (Msg) -> (Model) -> Model

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val messageView: TextView = findViewById(R.id.message_view)
        messageView.text = init().toString()

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val increment: Msg = Msg.Increment
            messageView.text = update()(increment)(Integer.parseInt(messageView.text as String)).toString()
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            val decrement: Msg = Msg.Decrement
            messageView.text = update()(decrement)(Integer.parseInt(messageView.text as String)).toString()
        }
    }


    //MODEL
    private fun init(): Model = 0


    //UPDATE
    private fun update(): update {
        return { msg ->
            when (msg) {
                is Msg.Increment -> { model ->
                    model + 1
                }
                is Msg.Decrement -> { model ->
                    model - 1
                }
            }
        }
    }
}
