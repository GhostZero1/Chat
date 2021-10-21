package com.example.chatv3

import android.app.Activity
import android.content.Context
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class ChatAdapter(private val messages:ArrayList<MessageChat>): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val textTextView: TextView
        val userName: TextView
        init {
            textTextView = view.findViewById(R.id.textTextView)
            userName = view.findViewById(R.id.userName)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userName.text = messages[position].name
        holder.textTextView.text = messages[position].text
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    fun add(message: MessageChat) {
        messages.add(message)
        notifyItemInserted(messages.size-1)

    }

}