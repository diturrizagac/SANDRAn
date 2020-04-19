package com.example.scoreregisterapp.presentation.qr

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.RestService
import com.example.scoreregisterapp.data.RestService.getRestProvider
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.data.repository.UserRepository
import com.example.scoreregisterapp.domain.entities.Course
import com.example.scoreregisterapp.domain.entities.Lesson
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.domain.model.LessonData
import com.example.scoreregisterapp.domain.model.QrData
import com.example.scoreregisterapp.domain.model.Role
import com.example.scoreregisterapp.domain.model.UserData
import com.example.scoreregisterapp.presentation.CreateLessonActivity
import com.example.scoreregisterapp.presentation.GradesListActivity
import com.example.scoreregisterapp.presentation.qr.qrUtil.EncryptionHelper
import com.example.scoreregisterapp.presentation.qr.qrUtil.QRCodeHelper
import com.google.gson.Gson
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel


class QrGenerateActivity : AppCompatActivity() {

    private val TAG = "QrGenerateActivity"

    private var qrCodeImageView: ImageView? = null
    private var userRepository = UserRepository

    private var currentLesson: Lesson? = null
    private var currentCourse: Course? = null

    private var currentStudentId: String? = null
    private var currentUserId: String? = null

    private var qrData: QrData? = null

    private var currentStudent: User? = null
    private var currentTeacherId: String? = null
    private var currentLessonId: String? = null
    private var currentCourseId: String? = null

    private var lessonData: LessonData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_generate)
        retrieveData()
        initializeUI()
        showQr()
    }

    private fun retrieveData() {
        val data = intent.extras?.getSerializable("data")
        if (data is User) {
            currentStudent = data
            currentStudentId = currentStudent?.objectId
        } else {
            lessonData = data as LessonData
            currentTeacherId = lessonData?.id_teacher
            currentLessonId = lessonData?.id_lesson
            currentCourseId = lessonData?.id_course
        }
        setQrData()
    }

    private fun setQrData() {
        qrData = QrData()
        qrData?.id_course = currentCourseId
        qrData?.id_lesson = currentLessonId
        qrData?.id_teacher = currentTeacherId
        qrData?.id_student = currentStudentId
    }

    private fun initializeUI() {
        qrCodeImageView = findViewById(R.id.qrCodeImageView)
    }

    private fun showQr() {
        hideKeyboard()

        /*qrData = if (currentStudent?.userRole.equals(Role.student.name)) {
            UserData(currentStudent?.objectId!!, currentStudent?.universityId!!)
        } else {
            lessonData
        }*/

        val serializeString = Gson().toJson(qrData)
        val encryptedString =
            EncryptionHelper.getInstance().encryptionString(serializeString).encryptMsg()
        setImageBitmap(encryptedString)
    }

    private fun setImageBitmap(encryptedString: String?) {
        val bitmap =
            QRCodeHelper.newInstance(this).setContent(encryptedString).setErrorCorrectionLevel(
                ErrorCorrectionLevel.Q
            ).setMargin(2).qrcOde
        qrCodeImageView?.setImageBitmap(bitmap)
    }

    /**
     * Hides the soft input keyboard if it is shown to the screen.
     */

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            } else {
                TODO("VERSION.SDK_INT < CUPCAKE")
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}
