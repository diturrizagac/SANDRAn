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
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.data.repository.UserRepository
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.domain.model.UserData
import com.example.scoreregisterapp.presentation.qr.qrUtil.EncryptionHelper
import com.example.scoreregisterapp.presentation.qr.qrUtil.QRCodeHelper
import com.google.gson.Gson
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel


class QrGenerateActivity : AppCompatActivity() {

    private val TAG = "QrGenerateActivity"

    private var userId: String? = null
    private var qrCodeImageView: ImageView? = null
    private var userRepository = UserRepository.getInstance()
    private var currentUser: User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_generate)
        retrieveData()
        initializeUI()
        //getUser()
        showQr()
    }

    private fun retrieveData() {
        currentUser = intent.extras?.getSerializable("data") as User
    }

    private fun initializeUI() {
        qrCodeImageView = findViewById(R.id.qrCodeImageView)
    }

    private fun showQr() {
        hideKeyboard()
        val user = UserData(currentUser?.objectId!!, currentUser?.universityId!!)
        val serializeString = Gson().toJson(user)
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


    private fun getUser() {
        userRepository.getUser(
            userId,
            object : OnGetItemCallback<User> {
                override fun onSuccess(item: User?) {
                    currentUser = item
                    showQr()
                }

                override fun onError() {
                    Log.e(TAG, "no trajo el item de internet")
                }

            }
        )

    }

}
