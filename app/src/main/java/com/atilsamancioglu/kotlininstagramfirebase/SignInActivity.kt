package com.atilsamancioglu.kotlininstagramfirebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }
    }


    fun signIn(view : View) {

        mAuth!!.signInWithEmailAndPassword(emailText.text.toString(),passwordText.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(applicationContext,FeedActivity::class.java)
                        startActivity(intent)
                    }
                }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }

    fun signUp(view : View) {

        mAuth!!.createUserWithEmailAndPassword(emailText.text.toString(),passwordText.text.toString())
                .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext,"User Created",Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,FeedActivity::class.java)
                startActivity(intent)
            }
        }.addOnFailureListener { exception ->
            if (exception != null) {
                Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }
}
