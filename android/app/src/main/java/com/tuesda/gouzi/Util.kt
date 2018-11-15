package com.tuesda.gouzi

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull

object Util {
    fun videoInfoOfUri(context: Context, uri: Uri): VideoInfo? {
        if (isMediaDocument(uri)) {
            val docId = DocumentsContract.getDocumentId(uri)
            val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val selection = "_id=?"
            val selectionArgs = arrayOf(split[1])

            val projection = arrayOf(
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.WIDTH,
                MediaStore.Video.Media.HEIGHT,
                MediaStore.Video.Media.DURATION
            )
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver.query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    projection, selection, selectionArgs, null
                )
                cursor?.apply {
                    if (moveToFirst()) {
                        val path = getStringOrNull(getColumnIndexOrThrow(MediaStore.Video.Media.DATA))
                        val width = getIntOrNull(getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)) ?: 1920
                        val height = getIntOrNull(getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)) ?: 1080
                        val durationUs =
                            getLongOrNull(getColumnIndexOrThrow(MediaStore.Video.Media.DURATION))?.let { it * 1000 }
                        if (path != null && durationUs != null) {
                            return VideoInfo(path, width, height, durationUs)
                        }
                    }
                }
            } catch (e: Exception) {
                GLog.e(e)
            } finally {
                cursor?.close()
            }
        }
        return null
    }

    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }
}