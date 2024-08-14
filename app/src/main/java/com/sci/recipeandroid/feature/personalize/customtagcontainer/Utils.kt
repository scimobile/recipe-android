package com.sci.recipeandroid.feature.personalize.customtagcontainer

import android.content.Context
import android.graphics.Color


object Utils {
    fun dp2px(context: Context, dp: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dp * scale + 0.5f
    }

    fun sp2px(context: Context, sp: Float): Float {
        val scale = context.resources.displayMetrics.scaledDensity
        return sp * scale
    }

    /**
     * If the color is Dark, make it lighter and vice versa
     *
     * @param color in int,
     * @param factor The factor greater than 0.0 and smaller than 1.0
     * @return int
     */
    fun manipulateColorBrightness(color: Int, factor: Float): Int {
        val a = Color.alpha(color)
        var r = Color.red(color)
        var g = Color.green(color)
        var b = Color.blue(color)
        //        if (r + b + g < 128 * 3) factor = 1 / factor;// check if the color is bright or dark
//        r = Math.round(r * factor);
//        b = Math.round(b * factor);
//        g = Math.round(g * factor);
        if (r > 127) r = 255 - Math.round((255 - r) * factor)
        if (g > 127) g = 255 - Math.round((255 - g) * factor)
        if (b > 127) b = 255 - Math.round((255 - b) * factor)
        return Color.argb(
            a,
            Math.min(r, 255),
            Math.min(g, 255),
            Math.min(b, 255)
        )
    }
}
