package com.example.echoclass.domain.model

import com.example.echoclass.R

data class Options(
    val name:String,
    val img:Int
) {
}

val options = listOf(
    Options("Lectures", R.drawable.lecture),
    Options("Live Class",R.drawable.liveclass),
    Options("Notes",R.drawable.notes)
)