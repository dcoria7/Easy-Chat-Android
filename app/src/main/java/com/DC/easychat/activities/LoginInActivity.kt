package com.DC.easychat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.DC.easychat.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginInActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null
    private var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_in)

        var firebaseDatabase = FirebaseDatabase.getInstance()

        mAuth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {

            var email = emailID.text.toString().trim()
            var psw = passwordID.text.toString().trim()

            if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(psw)){
                loginUser(email, psw)
            }else{
                Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun loginUser(email: String, password: String) {

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                task: Task<AuthResult> ->
                if(task.isSuccessful){
                    val intent = Intent(this, HomeActivity:: class.java)
                    var displayName = email.split("@")[0]
                    intent.putExtra("display_name", displayName)
                    this.startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "something wen wrong", Toast.LENGTH_LONG).show()
                }
            }

    }
}
