package com.example.scoreregisterapp.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.diturrizaga.easypay.util.NavigationTo.goTo
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.RestService.APP_ID
import com.example.scoreregisterapp.data.RestService.REST_API_KEY
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.presentation.ViewEventHandler.hideLoadingScreen
import com.example.scoreregisterapp.presentation.ViewEventHandler.setEventHandlerContext
import com.example.scoreregisterapp.presentation.ViewEventHandler.showLoadingScreen

class AuthenticationActivity : AppCompatActivity() {

    private var usernameEditText : EditText? = null;
    private var passwordEditText : EditText? = null
    private var loginButton: Button? = null
    private var userId : String? = null
    private val currentUser = User()
    private val TAG = "AuthenticationActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Backendless.initApp(this, APP_ID, REST_API_KEY)
        setContentView(R.layout.activity_authentication)
        setEventHandlerContext(this)
        initializeUI()
        setListeners()
    }

    private fun initializeUI() {
        usernameEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
    }

    private fun setListeners(){
        loginButton?.setOnClickListener {
            showLoadingScreen()
            getCurrentAccountFromBLService()
        }
    }

    private fun getCurrentAccountFromBLService() {
        Backendless.UserService.login(
            usernameEditText?.text.toString(),
            passwordEditText?.text.toString(),
            object : AsyncCallback<BackendlessUser> {
                override fun handleResponse(response: BackendlessUser?) {
                    response?.let { parseToUser(it) }
                    hideLoadingScreen()
                    goTo(UserHomeActivity::class.java,this@AuthenticationActivity, currentUser)
                    Toast.makeText(applicationContext,"${currentUser.firstName} has been logged in successfully", Toast.LENGTH_LONG).show()
                    loginButton!!.isEnabled = true
                }

                override fun handleFault(fault: BackendlessFault?) {
                    hideLoadingScreen()
                    Toast.makeText(applicationContext,fault?.message, Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    private fun parseToUser(backendlessUser : BackendlessUser) {
        currentUser.userRole = backendlessUser.properties?.getValue("user_role").toString()
        currentUser.email = backendlessUser.properties?.getValue("email").toString()
        currentUser.objectId = backendlessUser.properties?.getValue("objectId").toString()
        currentUser.universityId = backendlessUser.properties?.getValue("university_id").toString()
        currentUser.firstName = backendlessUser.properties?.getValue("first_name").toString()
        currentUser.midName = backendlessUser.properties?.getValue("mid_name").toString()
        currentUser.lastName = backendlessUser.properties?.getValue("last_name").toString()
        currentUser.mobileNumber = backendlessUser.properties?.getValue("mobile_number").toString()
        currentUser.studentCycle = backendlessUser.properties?.getValue("student_cycle").toString()
        //currentUser.lessons = backendlessUser.properties?.getValue("lessons") as Lesson
    }

}
