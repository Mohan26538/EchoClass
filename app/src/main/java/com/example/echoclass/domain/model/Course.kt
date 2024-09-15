package com.example.echoclass.domain.model

data class Course(
    val name:String = ""
)

val course = listOf(
    Course("Computer Science"),
    Course("Mathematics"),
    Course("Economics"),
    Course("Physics")
)
