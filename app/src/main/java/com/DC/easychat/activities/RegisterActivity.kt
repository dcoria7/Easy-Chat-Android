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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login_in.*
import kotlinx.android.synthetic.main.activity_main.*

class RegisterActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null
    private var mDatabase: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var firebaseDatabase = FirebaseDatabase.getInstance()

        mAuth = FirebaseAuth.getInstance()



        registerBtn.setOnClickListener {
            var email = emailID.text.toString().trim()
            var pwd = passwordID.text.toString().trim()
            // create new user
            mAuth!!.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener {
                    task: Task<AuthResult> ->
                if(task.isSuccessful){
                    currentUser = mAuth!!.currentUser!!
                    var userID = currentUser!!.uid

                    mDatabase = FirebaseDatabase.getInstance().reference.child("Users").child(userID)

                    var userObject = HashMap<String,String>()
                    var displayName = email.split("@")[0]
                    userObject.put("display_name", displayName)
                    userObject.put("email", email)

                    mDatabase!!.setValue(userObject).addOnCompleteListener{
                        task: Task<Void> ->
                        if(task.isSuccessful){
                            Toast.makeText(this,"user created", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, HomeActivity:: class.java)
                            this.startActivity(intent)
                            finish()
                        }else{
                            Log.d("Error", task.toString())
                        }
                    }


                }else{
                    Log.d("Error", task.toString())
                }

            }

        }
    }
}
