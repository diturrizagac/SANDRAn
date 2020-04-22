package com.example.scoreregisterapp.presentation.custom


import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar

class CustomProgressBarHandler {

    private var mProgressBar : ProgressBar? = null
    private var mContext: Context? = null

    constructor(context: Context?) {
        mContext = context
        val currentLayout: ViewGroup = (context as Activity)
            .findViewById<View>(android.R.id.content)
            .rootView as ViewGroup

        mProgressBar = ProgressBar(context, null, android.R.attr.progressBarStyle)
        mProgressBar?.isIndeterminate = true

        val lParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)
        val linearLayout: LinearLayout = LinearLayout(context)
        linearLayout.gravity = Gravity.CENTER
        linearLayout.addView(mProgressBar)
        currentLayout.addView(linearLayout, lParams)

        hideLoadingScreen()
    }

    fun showLoadingScreen() {
        mProgressBar?.visibility = View.VISIBLE
    }

    fun hideLoadingScreen() {
        mProgressBar?.visibility = View.INVISIBLE
    }
}