package com.example.scoreregisterapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.scoreregisterapp.R

class UserProfileActivity : AppCompatActivity() {

    private val kUserProfileTitle = "USER PROFILE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setNavigationToolbar()
    }


    private fun setNavigationToolbar() {

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        /*val toolbar = findViewById<Toolbar>(R.id.toolbar)
        ViewEventHandler.addToolbarToContext(toolbar)
        ViewEventHandler.addLogOutButton()
        ViewEventHandler.addContextTitle(kUserProfileTitle)
        setSupportActionBar(toolbar)*/
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}
