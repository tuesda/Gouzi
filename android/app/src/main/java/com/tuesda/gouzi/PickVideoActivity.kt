package com.tuesda.gouzi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View


class PickVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_video)
        val btnPickVideo = findViewById<View>(R.id.btn_pick_video)
        btnPickVideo.setOnClickListener {
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "video/*"
            startActivityForResult(Intent.createChooser(i, "Select Video"), PICK_VIDEO_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_VIDEO_REQUEST_CODE && data != null) {
            data.data.apply {
                Log.i(TAG, "video uri $this path ${pathOfUri(this@PickVideoActivity, this)}")
            }
        }
    }

    companion object {
        const val TAG = "PickVideoActivity"
        const val PICK_VIDEO_REQUEST_CODE = 0

        private fun pathOfUri(context: Context, uri: Uri): String? {
            // https://stackoverflow.com/questions/13209494/how-to-get-the-full-file-path-from-uri
            val splits = DocumentsContract.getDocumentId(uri).split(":")
            val cursor = context.contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(MediaStore.Video.Media.DATA), "_id=?", arrayOf(splits[1]), null)
            return cursor?.run {
                val columnIndex = getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
                val result = if (moveToFirst()) {
                    getString(columnIndex)
                } else {
                    null
                }
                close()
                result
            }
        }
    }
}