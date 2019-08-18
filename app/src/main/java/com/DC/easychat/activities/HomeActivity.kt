package com.DC.easychat.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.DC.easychat.R
import com.DC.easychat.adapters.SectionPagerAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var sectionAdapter : SectionPagerAdapter? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //supportActionBar!!.title = "Home"

        sectionAdapter = SectionPagerAdapter(supportFragmentManager)
        homeViewPagerId.adapter = sectionAdapter
        mainTabs.setupWithViewPager(homeViewPagerId)

        mainTabs.setTabTextColors(Color.WHITE, Color.RED)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if(item != null){
            if(item.itemId == R.id.logoutId){
                // logout user
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this,MainActivity:: class.java))
                finish()
            }
        }


        return true
    }
}
