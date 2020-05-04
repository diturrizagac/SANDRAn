package com.example.scoreregisterapp.presentation

import android.content.Context
import androidx.appcompat.widget.Toolbar
import com.example.scoreregisterapp.presentation.custom.CustomProgressBarHandler
import com.example.scoreregisterapp.presentation.custom.CustomToolbarHandler

object ViewEventHandler {
    private var progressBar: CustomProgressBarHandler? = null
    private var toolbar: CustomToolbarHandler? = null
    var mContext: Context? = null


    fun setEventHandlerContext(context: Context) {
        mContext = context
        setEventUp()
        setBarToolbarNavigation()
    }

    private fun setEventUp() {
        progressBar = CustomProgressBarHandler(mContext)
    }

    private fun setBarToolbarNavigation() {
        toolbar = CustomToolbarHandler(mContext)
    }

    fun addToolbarToContext(cToolbar: Toolbar) {
        toolbar?.setUpToolbarToContext(cToolbar)
    }
    fun addContextTitle(title: String) {
        toolbar?.setToolbarTitle(title)
    }

    fun addLogOutButton(){
        toolbar?.setUpToolbarWithSignOff()
    }

    fun addBackButton() {
        toolbar?.setUpToolbarWithLeftButton()
    }

    fun addRightButton() {
        toolbar?.setUpToolbarWithRightButton()
    }


    fun showLoadingScreen() {
        progressBar?.showLoadingScreen()
    }

    fun hideLoadingScreen() {
        progressBar?.hideLoadingScreen()
    }


}