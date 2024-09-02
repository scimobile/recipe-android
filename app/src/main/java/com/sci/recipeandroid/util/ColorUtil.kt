package com.sci.recipeandroid.util

import android.graphics.Color

object ColorUtil {
     fun blendColors(startColor: Int, endColor: Int, fraction: Float): Int {
        val startAlpha = Color.alpha(startColor)
        val startRed = Color.red(startColor)
        val startGreen = Color.green(startColor)
        val startBlue = Color.blue(startColor)

        val endAlpha = Color.alpha(endColor)
        val endRed = Color.red(endColor)
        val endGreen = Color.green(endColor)
        val endBlue = Color.blue(endColor)

        val blendedAlpha = (startAlpha + ((endAlpha - startAlpha) * fraction)).toInt()
        val blendedRed = (startRed + ((endRed - startRed) * fraction)).toInt()
        val blendedGreen = (startGreen + ((endGreen - startGreen) * fraction)).toInt()
        val blendedBlue = (startBlue + ((endBlue - startBlue) * fraction)).toInt()

        return Color.argb(blendedAlpha, blendedRed, blendedGreen, blendedBlue)
    }

}