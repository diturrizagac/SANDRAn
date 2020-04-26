package com.example.scoreregisterapp.presentation.custom

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.scoreregisterapp.R


class CustomToolbarHandler {

    private var mToolbar: Toolbar? = null
    private var mContext: Context? = null
    private var currentLayout: ViewGroup? = null

    private var layoutParams: Toolbar.LayoutParams? = null

    constructor(context: Context?){
        mContext = context
        currentLayout = (mContext as Activity)
            .findViewById<View>(android.R.id.content)
            .rootView as ViewGroup
    }

    fun setUpToolbarToContext(mToolbar: Toolbar) {
        //mToolbar = Toolbar(mContext)
        this.mToolbar = mToolbar
        layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        /*val linearLayout = LinearLayout(mContext)

        linearLayout.gravity = Gravity.CENTER
        linearLayout.addView(mToolbar)

        currentLayout?.addView(linearLayout, layoutParams)*/
    }



    fun setUpToolbarWithSignOff() {
        val toolbarRightButton = Button(mContext)
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.START
        toolbarRightButton.layoutParams = layoutParams
        val typeFace = Typeface.createFromAsset(mContext?.assets, "fonts/nunito_black.ttf")
        toolbarRightButton.typeface = typeFace
        toolbarRightButton.text = "SIGN OFF"
        mToolbar?.addView(toolbarRightButton)
    }

    fun setUpToolbarWithLeftButton() {
        val toolbarRightButton = Button(mContext)
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.START
        toolbarRightButton.layoutParams = layoutParams
        toolbarRightButton.setBackgroundResource(R.drawable.back_arrow_vector_icon)
        mToolbar?.addView(toolbarRightButton)
    }

    fun setToolbarTitle(title: String) {
        val toolbarTitle = TextView(mContext)
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        toolbarTitle.layoutParams = layoutParams
        val typeFace = Typeface.createFromAsset(mContext?.assets, "fonts/nunito_black.ttf")
        toolbarTitle.typeface = typeFace
        toolbarTitle.text = title
        mToolbar?.addView(toolbarTitle)
    }

    fun setUpToolbarWithRightButton() {
        val toolbarLeftButton = Button(mContext)
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.END
        toolbarLeftButton.layoutParams = layoutParams
        toolbarLeftButton.text = "CONTINUE"
        mToolbar?.addView(toolbarLeftButton)
    }

}