package com.example.chatv3

import android.app.Activity
import android.content.Context
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class ChatAdapter: ArrayAdapter <MessageChat> {

    constructor(context: Context, resourse: Int, messages: List<MessageChat>) : super(context, resourse, messages)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        if (convertView == null)
//            convertView = (getContext() as Activity).getLayoutInflater().inflate(R.layout.message_item, parent, false)

        var textEditText:TextView = convertView!!.findViewById(R.id.textEditText)
        var nameTextView:TextView = convertView.findViewById(R.id.userName)
        var messanges:ListView = convertView.findViewById(R.id.messanges)



        var message:MessageChat = getItem(position)!!
        nameTextView.setText(message.name)

        return convertView
    }

   // fun setText()
}