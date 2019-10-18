package com.example.scoreregisterapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scoreregisterapp.R

class AuthenticationActivity : AppCompatActivity() {

    private val TAG = "AuthenticationActivity"
    private val REQUEST_SIGNUP = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
    }

    fun initializeUI() {

    }


}
