package com.tuesda.gouzi

class VideoInfo(
    private val path: String,
    private val width: Int,
    private val height: Int,
    private val durationUs: Long
) {
    override fun toString(): String {
        return "VideoInfo(path='$path', width=$width, height=$height, durationUs=$durationUs)"
    }
}