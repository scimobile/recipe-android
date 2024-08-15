package com.sci.recipeandroid.util

import com.tencent.mmkv.MMKV

object MMKVUtil {
    private const val KEY_ACCESS_TOKEN = "accessToken"
    var token:String?
        get() = MMKV.defaultMMKV().decodeString(KEY_ACCESS_TOKEN,null)
        set(value)  {
            MMKV.defaultMMKV().encode(KEY_ACCESS_TOKEN,value)
        }
}