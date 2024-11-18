package com.sci.recipeandroid.util

import android.content.Intent
import android.content.Intent.ACTION_SEND

class SharedIntentBuilder(private val message: String) {

    private val intent = Intent().setAction(ACTION_SEND)
        .putExtra(Intent.EXTRA_TEXT, message)
        .setType("text/plain")

    private val messengerIntent = Intent(intent).setPackage("com.facebook.orca")
    private val teleIntent = Intent(intent).setPackage("org.telegram.messenger")
    private val viberIntent = Intent(intent).setPackage("com.viber.voip")
    private val whatsappIntent = Intent(intent).setPackage("com.whatsapp")

    private val initialTargetArray = arrayOf(
        messengerIntent,
        teleIntent,
        viberIntent, whatsappIntent
    )
    private val initialChooser = arrayOf(
        Intent.createChooser(messengerIntent, "Share your recipe"),
        Intent.createChooser(teleIntent, "Share your recipe"),
        Intent.createChooser(viberIntent, "Share your recipe"),
        Intent.createChooser(whatsappIntent, "Share your recipe")
    )

    fun textSenderIntent(): Intent = Intent.createChooser(
        intent, "Share your recipe"
    ).apply {
        putExtra(Intent.EXTRA_INITIAL_INTENTS, initialTargetArray)
        putExtra(Intent.EXTRA_CHOOSER_TARGETS, initialChooser)
    }


}