package com.DC.easychat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.DC.easychat.R
import kotlinx.android.synthetic.main.activity_login_in.*
import kotlinx.android.synthetic.main.activity_main.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity:: class.java)
            this.startActivity(intent)
        }
    }
}
