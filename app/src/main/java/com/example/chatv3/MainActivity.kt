package com.example.chatv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var messages: ListView
    lateinit var adapter : ChatAdapter
    lateinit var sendButton : Button
    lateinit var userName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendButton = findViewById(R.id.sendButton)
        var userName = "Default User"
        var messanges = findViewById<ListView>(R.id.messanges)
        var chatMessages: List<MessageChat> = ArrayList<MessageChat>()
        var adapter = ChatAdapter(this, R.layout.message_item, chatMessages)
        messanges.adapter = adapter
        textEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendButton.isEnabled = s.toString().trim().isNotEmpty()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        textEditText.setFilters(arrayOf<InputFilter>(InputFilter.LengthFilter(100)))
        sendButton.setOnClickListener(View.OnClickListener {
            textEditText.setText("")
        })

    }



    }

