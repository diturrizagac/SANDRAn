package com.example.scoreregisterapp.presentation.qr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.repository.UserRepository
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.presentation.qr.qrUtil.EncryptionHelper
import com.google.gson.Gson
import java.lang.RuntimeException

class QrResultActivity : AppCompatActivity() {

    private var TAG = "WithdrawalScannedActivity"
    private var scannedFullNameTextView : TextView? = null
    private var scannedAgeTextView : TextView? = null


    private var  scannedUserName : TextView? = null
    private var  scannedUserUniversityId : TextView? = null
    private var  scannedUserCourseName : TextView? = null

    private var user: User? = null
    private var userRepository = UserRepository.getInstance()
    private var valueScanned : String? = null


    companion object {
        private const val SCANNED_STRING: String = "scanned_string"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_result)
        initializeUI()
        retrieveData()
        setData()
    }


    /**
     *
     * RETRIEVE SCANNED DATA FROM CAMERA
     */

    private fun retrieveData() {
        if (intent.getSerializableExtra(SCANNED_STRING) == null)
            throw RuntimeException("No encrypted String found in intent")
        /**
         * recently implemented
         */
        valueScanned = intent.getSerializableExtra(SCANNED_STRING) as String
        if (valueScanned == null) {
            throw RuntimeException("No encrypted String found in intent")
        }
        user = intent.extras!!.getSerializable("account") as User
    }

    /**
     * CALLING SERVER TO SAVE DATA
     */

    /*private fun createAttendance() {
        transactionRepository.createTransaction(
            currentTransaction!!,
            object : OnPostItemCallback<Transaction> {
                override fun onSuccess(item: Transaction) {
                    transaction = item
                    Log.v(TAG, transaction!!.from_account)
                }

                override fun onError() {
                    Log.v(TAG, "Couldn't bring data from URL")
                }
            }
        )
    }*/

    private fun setData() {
        val decryptedString = EncryptionHelper.getInstance().getDecryptionString(valueScanned)
        user = Gson().fromJson(decryptedString, User::class.java)

        scannedUserName!!.text = "${user?.firstName} ${user?.lastName}"
        scannedUserUniversityId!!.text = user?.universityId

    }

    private fun initializeUI() {
        scannedUserName = findViewById(R.id.scanned_user_name)
        scannedUserUniversityId = findViewById(R.id.scanned_user_university_id)
        scannedUserCourseName = findViewById(R.id.scanned_user_course)
    }
}
