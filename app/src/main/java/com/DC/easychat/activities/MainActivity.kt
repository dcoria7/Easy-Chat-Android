package com.DC.easychat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.DC.easychat.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var firebaseDatabase = FirebaseDatabase.getInstance()

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {
            firebaseAuth: FirebaseAuth ->
            currentUser =  firebaseAuth.currentUser

            if(currentUser != null){

                Log.d("user:", currentUser!!.email)
                //Toast.makeText(this, "User is logged in", Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity:: class.java)
                var displayName = currentUser!!.email!!.split("@")[0]
                intent.putExtra("display_name", displayName)
                this.startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "no signed in", Toast.LENGTH_LONG).show()
            }
        }


        // Action buttons
        loginBtn.setOnClickListener{
            val intent = Intent(this, LoginInActivity:: class.java)
            this.startActivity(intent)

        }

        registerBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity:: class.java)
            this.startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListener!!)


    }

    override fun onStop() {
        super.onStop()
        if(mAuthListener != null){
            mAuth!!.removeAuthStateListener { mAuthListener!!}
        }
    }
}
