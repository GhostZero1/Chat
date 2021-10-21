package com.example.chatv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var messages: ListView
    lateinit var adapter: ChatAdapter
    lateinit var sendButton: Button
    lateinit var userName: String
    lateinit var database: Firebase;
    lateinit var databaseReference: DatabaseReference
    lateinit var messagesChildEventListener: ChildEventListener


    //lateinit var userNameDatabaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val databaseReference = database.getReference().child("messages")

//
//        databaseReference.setValue("Hello, World!")

        sendButton = findViewById(R.id.sendButton)
        var userName = "Default User"
        var messangesListView = findViewById<RecyclerView>(R.id.messanges)
        var chatMessages: List<MessageChat> = ArrayList<MessageChat>()
        var adapter = ChatAdapter(chatMessages as ArrayList<MessageChat>)
        var linearLayout: LinearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        messangesListView.layoutManager = linearLayout
        messangesListView.adapter = adapter
        textEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendButton.isEnabled = s.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        textEditText.setFilters(arrayOf<InputFilter>(InputFilter.LengthFilter(100)))
        sendButton.setOnClickListener(View.OnClickListener {

            var message: MessageChat =
                MessageChat(text = textEditText.text.toString(), name = userName)
            // message.text = textEditText.text.toString()
            databaseReference.push().setValue(message)
            textEditText.setText("")
        })
        val messagesChildEventListener = object : ChildEventListener {

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // A new message has been added
                // onChildAdded() will be called for each node at the first time
//                val message = dataSnapshot!!.getValue(Message::class.java)
//                messageList.add(message!!)
                val message = snapshot.getValue(MessageChat::class.java)
                if (message != null) {
                    adapter.add(message)
                }
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//                Log.e(TAG, "onChildChanged:" + dataSnapshot!!.key)
//
//                // A message has changed
//                val message = dataSnapshot.getValue(Message::class.java)
            }
            override fun onChildRemoved(snapshot: DataSnapshot) {
//                Log.e(TAG, "onChildRemoved:" + dataSnapshot!!.key)
//
//                // A message has been removed
//                val message = dataSnapshot.getValue(Message::class.java)
            }
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//                Log.e(TAG, "onChildMoved:" + dataSnapshot!!.key)
//
//                // A message has changed position
//                val message = dataSnapshot.getValue(Message::class.java)
            }

            override fun onCancelled(error: DatabaseError) {
                //Log.e(TAG, "postMessages:onCancelled", databaseError!!.toException())
            }


        }
        databaseReference.addChildEventListener(messagesChildEventListener)
    }


    }












