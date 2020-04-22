package com.example.scoreregisterapp.presentation

import android.content.Context
import com.example.scoreregisterapp.presentation.custom.CustomProgressBarHandler

object ViewEventHandler {
    var progressBar: CustomProgressBarHandler? = null
    var mContext: Context? = null


    fun setEventHandlerContext(context: Context) {
        mContext = context
        setEventUp()
    }

    private fun setEventUp() {
        progressBar = CustomProgressBarHandler(mContext)
    }

    fun showLoadingScreen() {
        progressBar?.showLoadingScreen()
    }

    fun hideLoadingScreen() {
        progressBar?.hideLoadingScreen()
    }
}