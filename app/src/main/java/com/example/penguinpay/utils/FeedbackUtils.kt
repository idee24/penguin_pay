package com.example.penguinpay.utils

import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 *@Created by Yerimah on 5/07/2021.
 */

fun Fragment.showLoader(showLoader: Boolean, appLoader: RelativeLayout, activity: AppCompatActivity) {

    if (showLoader) {
        appLoader.visibility = View.VISIBLE
        appLoader.hideKeyboard()
        activity.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
    else {
        appLoader.visibility = View.GONE
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}

fun View.hideKeyboard() {
    val hideAction = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    hideAction.hideSoftInputFromWindow(windowToken, 0)
}