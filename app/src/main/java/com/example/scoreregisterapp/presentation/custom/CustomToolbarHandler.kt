package com.example.scoreregisterapp.presentation.custom

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.scoreregisterapp.R


class CustomToolbarHandler {

    private var mToolbar: Toolbar? = null
    private var mContext: Context? = null
    private var rightButton: Button? = null
    private var leftButton: Button? = null

    constructor(context: Context?){
        mContext = context
    }

    fun setUpToolbarToContext(toolbar: Toolbar) {
        mToolbar = toolbar
    }

    fun setUpToolbarWithSignOff() {
        val toolbarLeftButton = Button(mContext)
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.START
        toolbarLeftButton.layoutParams = layoutParams
        val typeFace = Typeface.createFromAsset(mContext?.assets, "fonts/nunito_black.ttf")
        toolbarLeftButton.typeface = typeFace
        toolbarLeftButton.setBackgroundColor(Color.parseColor("#83160C"))
        //toolbarRightButton.setBackgroundColor(Color.parseColor("@color/sanMarcosMainColor"))
        toolbarLeftButton.text = "SIGN OFF"
        mToolbar?.addView(toolbarLeftButton)
        setLeftButton(toolbarLeftButton)
    }

    fun setUpToolbarWithRightButton() {
        val toolbarLeftButton = Button(mContext)
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.END
        toolbarLeftButton.layoutParams = layoutParams
        toolbarLeftButton.text = "CONTINUE"
        mToolbar?.addView(toolbarLeftButton)
        setLeftButton(toolbarLeftButton)
    }

    fun setUpToolbarWithLeftButton() {
        val toolbarRightButton = Button(mContext)
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.START
        toolbarRightButton.layoutParams = layoutParams
        toolbarRightButton.setBackgroundResource(R.drawable.back_arrow_vector_icon)
        mToolbar?.addView(toolbarRightButton)
        setRightButton(toolbarRightButton)
    }

    fun setToolbarTitle(title: String) {
        val toolbarTitle = TextView(mContext)
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        toolbarTitle.layoutParams = layoutParams
        val typeFace = Typeface.createFromAsset(mContext?.assets, "fonts/nunito_black.ttf")
        toolbarTitle.textSize = "20.00".toFloat()
        toolbarTitle.typeface = typeFace
        toolbarTitle.text = title
        mToolbar?.addView(toolbarTitle)
    }

    fun setRightButton(button: Button?) {
        rightButton = button
    }

    fun setLeftButton(button: Button?) {
        leftButton = button
    }

    fun onBackButtonNavigation() {
        //handleBackNavigation
    }

}