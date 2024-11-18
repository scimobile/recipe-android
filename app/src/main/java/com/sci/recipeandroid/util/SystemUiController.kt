package com.sci.recipeandroid.util

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams

object SystemUiController {
    fun adjustStatusBar(
        view: View,
        activity: Activity,
        statusBarColor: Int
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
            }
            WindowInsetsCompat.CONSUMED
        }
        val window = activity.window
        window.statusBarColor = statusBarColor
    }

    fun adjustNavigationBar(
        view: View,
        activity: Activity,
        navigationBarColor: Int
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = insets.bottom
            }
            WindowInsetsCompat.CONSUMED
        }
        val window = activity.window
        window.navigationBarColor = navigationBarColor

    }
}