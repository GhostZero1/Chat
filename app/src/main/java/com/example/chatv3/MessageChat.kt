package com.example.chatv3

import com.google.android.datatransport.runtime.dagger.Provides
import org.w3c.dom.Text
import java.awt.font.TextAttribute
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Constructor

//class MessageChat(
//    var text: String = "DEFAULT_VALUE",
//    var name: String = "DEFAULT_VALUE"
//    //var imageUrl: String
//
//)
//{
//
//}
data class MessageChat(var text: String = "dfsd",
                       var name: String = "sfdvsfbv"
                       )

//    @Provides
//    fun provideText(): String {
//        return text;
//
//    }
//    @Provides
//    fun provideName(): String {
//        return name;
//
//    }
////    lateinit var text: String
////    lateinit var name: String
//    private lateinit var iageUrl: String
//
//
//    override fun toString(): String {
//        return "MessageChat(text='$text', name='$name', imageUrl='$imageUrl', iageUrl='$iageUrl')"
    //}

