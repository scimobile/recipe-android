package com.sci.recipeandroid.util

import android.content.Context
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.*

class MyCustomContext(private val myContext: Context) : CoroutineContext.Element {

    override val key: CoroutineContext.Key<*> = Key
    val context = myContext

    companion object Key :
        CoroutineContext.Key<MyCustomContext>
}
