package com.tuesda.gouzi

import android.util.Log

object GLog {

    private const val TAG = "GLog"

    fun i(tag: String, info: String) {
        Log.i(tag, info)
    }

    fun e(t: Throwable) {
        Log.e(TAG, t.toString())
    }
}