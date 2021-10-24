package com.example.chatv3

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sing_in.*
import kotlinx.android.synthetic.main.activity_sing_in.view.*
import kotlinx.android.synthetic.main.message_item.*

class SingInActivity : AppCompatActivity() {


    private lateinit var usersDatabaseReference: DatabaseReference
    lateinit var database: Firebase
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_sing_in)
        var emailEditText = findViewById<EditText>(R.id.etEmail)
        var passwordEditText = findViewById<EditText>(R.id.etPassword)
        val database = Firebase.database
        var nameEditText = findViewById<EditText>(R.id.etNewUserDisplayName)
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        usersDatabaseReference = database.reference.child("users");

        signUpLogInButton.setOnClickListener {
            singUpUser(
                email = emailEditText.text.toString().trim(),
                password = passwordEditText.text.toString().trim()
            )
        }
    }

    private fun singUpUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@SingInActivity) { task ->
                println(task.isSuccessful)
                if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Sign", "createUserWithEmail:success")
                        val user = auth.currentUser
                        createUser(user!!)
                        val intent = Intent(this@SingInActivity, MainActivity::class.java)
                        intent.putExtra("userName", etNewUserDisplayName.text.toString().trim())
                        startActivity(intent)
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Sign", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@SingInActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null)
                    }
            }
       // startActivity(Intent(this@SingInActivity,MainActivity::class.java))
    }

    private fun createUser(firebaseUser: FirebaseUser){
        val user = User(
            firebaseUser.uid,
            firebaseUser.email!!,
            etNewUserDisplayName.text.toString()
        )

        usersDatabaseReference.push().setValue(user)
    }


}











