package com.sci.recipeandroid.util

import android.os.SystemClock
import android.view.View

class OneTimeClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}

/**
 * Set a [OneTimeClickListener] on a view
 */
fun View.setOneTimeClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = OneTimeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}