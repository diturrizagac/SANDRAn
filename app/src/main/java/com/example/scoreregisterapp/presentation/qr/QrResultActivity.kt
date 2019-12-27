package com.example.scoreregisterapp.presentation.qr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.RestService.getRestProvider
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.data.callback.OnPostItemCallback
import com.example.scoreregisterapp.data.repository.AttendanceRepository
import com.example.scoreregisterapp.data.repository.UserRepository
import com.example.scoreregisterapp.domain.entities.Attendance
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.domain.model.QrData
import com.example.scoreregisterapp.presentation.qr.qrUtil.EncryptionHelper
import com.google.gson.Gson

class QrResultActivity : AppCompatActivity() {

    private val TAG = "QrResultActivity"
    private val CLASS = "attendance"

    private var scannedTitle: TextView? = null
    private var scannedSubtitle1: TextView? = null
    private var scannedSubtitle2: TextView? = null
    private var scannedSubtitle3: TextView? = null
    private var successful_announcement : TextView? = null

    private var currentUser: User? = null
    private var userId: String? = null
    private var userRepository = UserRepository(getRestProvider()).getInstance()
    private var valueScanned: String? = null

    private val attendanceRepository = AttendanceRepository.getInstance()
    private var currentAttendance: Attendance? = null
    private var currentAttendanceId: String? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_result)
        initializeUI()
        retrieveDataFromQR()
        setAttendanceValues()
    }

    private fun setAttendanceValues() {
        currentAttendance = Attendance()
        currentAttendance?.check_in = "proof"
        currentAttendance?.id_student = userId
        currentAttendance?.ownerId = userId
        currentAttendance?._class = CLASS
    }

    /**
     * RETRIEVE SCANNED DATA FROM CAMERA
     */

    private fun retrieveDataFromQR() {
        valueScanned = intent.extras!!.getString("userId")
        val decryptedString = EncryptionHelper.getInstance().getDecryptionString(valueScanned)
        val qrData = Gson().fromJson(decryptedString, QrData::class.java)
        userId = intent.extras!!.getString("role")
        if (userId == "" || userId == null) {
            userId = qrData.id_teacher
        }

        getUser()
    }

    /**
     * CALLING SERVER TO SAVE DATA
     */

    private fun createAttendance() {
        attendanceRepository.postAttendance(
            currentAttendance,
            object : OnPostItemCallback<Attendance> {
                override fun onSuccess(item: Attendance?) {
                    currentAttendance = item
                    Log.v(TAG, "Data attendance posted")
                    successful_announcement?.isVisible = true
                }

                override fun onError() {
                    Log.v(TAG, "Couldn't bring data from URL of Attendance")
                }

            }
        )
    }


    private fun getUser() {
        userRepository.getUser(
            userId!!,
            object : OnGetItemCallback<User> {
                override fun onSuccess(item: User?) {
                    currentUser = item
                    setData()
                    createAttendance()
                }

                override fun onError() {
                    Log.v(TAG, "Couldn't bring data from URL of User")
                }

            }
        )
    }

    private fun setData() {
        scannedTitle!!.text = currentUser?.firstName.toString()
        scannedSubtitle1!!.text = currentUser?.universityId.toString()
    }

    private fun initializeUI() {
        scannedTitle = findViewById(R.id.scanned_title)
        scannedSubtitle1 = findViewById(R.id.scanned_subtitle)
        scannedSubtitle2 = findViewById(R.id.scanned_subtitle_2)
        scannedSubtitle3 = findViewById(R.id.scanned_subtitle_3)
        successful_announcement = findViewById(R.id.successful_announcement)
        successful_announcement?.isVisible = false
    }

    private fun setRelationOnBackendless(attendance: Attendance, transaction: Attendance) {
        val transactionCollection = ArrayList<Attendance>()
        transactionCollection.add(transaction)
        Backendless.Data.of(Attendance::class.java).addRelation(
            attendance,
            "transaction",
            transactionCollection,
            object : AsyncCallback<Int> {
                override fun handleFault(fault: BackendlessFault?) {
                    Log.e("EASYPAY", "server reported an error - " + fault!!.message)
                    Toast.makeText(applicationContext, fault.message, Toast.LENGTH_LONG).show()
                }

                override fun handleResponse(response: Int?) {
                    Toast.makeText(applicationContext, "Data  $response", Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}
