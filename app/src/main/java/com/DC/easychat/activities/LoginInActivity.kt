package com.DC.easychat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.DC.easychat.R
import kotlinx.android.synthetic.main.activity_main.*

class LoginInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_in)

        loginBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity:: class.java)
            this.startActivity(intent)
        }
    }
}
