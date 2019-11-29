package com.example.scoreregisterapp.presentation.qr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.data.repository.UserRepository
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.domain.model.UserData
import com.example.scoreregisterapp.presentation.qr.qrUtil.EncryptionHelper
import com.google.gson.Gson

class QrResultActivity : AppCompatActivity() {

    private var TAG = "QrResultActivity"

    private var  scannedUserName : TextView? = null
    private var  scannedUserUniversityId : TextView? = null
    private var  scannedUserCourseName : TextView? = null

    private var currentUser: User? = null
    private var userId : String? = null
    private var userRepository = UserRepository.getInstance()
    private var valueScanned : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_result)
        initializeUI()
        retrieveData()
    }

    /**
     *
     * RETRIEVE SCANNED DATA FROM CAMERA
     */

    private fun retrieveData() {
        valueScanned = intent.extras!!.getString("userId")
        val decryptedString = EncryptionHelper.getInstance().getDecryptionString(valueScanned)
        val userData = Gson().fromJson(decryptedString, UserData::class.java)
        userId = userData.objectId
        getUser()
    }

    /**
     * CALLING SERVER TO SAVE DATA
     */

    private fun createAttendance() {


    }

    private fun getUser() {
        userRepository.getUser(
            userId!!,
            object : OnGetItemCallback<User> {
                override fun onSuccess(item: User) {
                    currentUser = item
                    setData()

                }

                override fun onError() {
                    Log.e(TAG, "no trajo el item de internet")
                }

            }
        )
    }

    private fun setData() {
        scannedUserName!!.text = currentUser?.firstName.toString()
        scannedUserUniversityId!!.text = currentUser?.universityId.toString()

    }

    private fun initializeUI() {
        scannedUserName = findViewById(R.id.scanned_user_name)
        scannedUserUniversityId = findViewById(R.id.scanned_user_university_id)
        scannedUserCourseName = findViewById(R.id.scanned_user_course)
    }
}
