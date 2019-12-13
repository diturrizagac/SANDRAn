package com.example.scoreregisterapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.diturrizaga.easypay.util.NavigationTo.goTo
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.RestService.APP_ID
import com.example.scoreregisterapp.data.RestService.REST_API_KEY
import com.example.scoreregisterapp.domain.entities.User

class AuthenticationActivity : AppCompatActivity() {

    private var usernameEditText : EditText? = null;
    private var passwordEditText : EditText? = null
    private var loginButton: Button? = null
    private var userId : String? = null
    private val currentUser = User()
    private val TAG = "AuthenticationActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        Backendless.initApp(this, APP_ID, REST_API_KEY)
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
            setUpAccount()
        }
    }

    private fun setUpAccount() {
        Backendless.UserService.login(
            usernameEditText?.text.toString(),
            passwordEditText?.text.toString(),
            object : AsyncCallback<BackendlessUser> {
                override fun handleFault(fault: BackendlessFault?) {
                    Toast.makeText(applicationContext,fault?.message, Toast.LENGTH_LONG).show()
                }

                override fun handleResponse(response: BackendlessUser?) {
                    response?.let { parseToUser(it) }
                    Toast.makeText(applicationContext,"${currentUser.firstName} has been logged in successfully", Toast.LENGTH_LONG).show()
                    /*val activity = if(currentUser.userRole == Role.student.name){
                        StudentHomeActivity::class.java
                    } else {
                        TeacherHomeActivity::class.java
                    }*/
                    //goTo(activity,this@AuthenticationActivity, currentUser.objectId, currentUser.userRole)
                    goTo(UserHomeActivity::class.java,this@AuthenticationActivity, currentUser)

                    loginButton!!.isEnabled = true
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
