package com.sci.recipeandroid.util

import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sci.recipeandroid.R

val <T> T.exhaustive: T
    get() = this

fun Fragment.updateStatusBarColors(
    statusBarColorResId: Int = R.color.color_status_bar_bg_white_transparent,
    useDarkIcons: Boolean = true
) {
    activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), statusBarColorResId)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val controller = activity?.window?.insetsController
        controller?.setSystemBarsAppearance(
            if (useDarkIcons) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        @Suppress("DEPRECATION")
        activity?.window?.decorView?.systemUiVisibility = if (useDarkIcons) {
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            0
        }
    }
}