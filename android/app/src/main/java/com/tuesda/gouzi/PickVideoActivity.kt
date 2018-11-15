package com.tuesda.gouzi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


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
            data.data?.let { Util.videoInfoOfUri(this, it) }.also {
                GLog.i(TAG, "picked video info: $it")
            }
        }
    }

    companion object {
        const val TAG = "PickVideoActivity"
        const val PICK_VIDEO_REQUEST_CODE = 0
    }
}