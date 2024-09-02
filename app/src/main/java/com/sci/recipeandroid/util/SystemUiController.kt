package com.sci.recipeandroid.util

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams

object SystemUiController {
    fun adjustStatusBar(
        toolBar: View,
        activity: Activity
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(toolBar) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
            }
            WindowInsetsCompat.CONSUMED
        }
        val window = activity.window
        window.statusBarColor = Color.WHITE
    }
}