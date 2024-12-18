package com.example.carbontrace.model

data class User (
    val uid: Int?,
    val username: String,
    val password: String,
    val grade: Int,
    val carbons: Int,
    val points: Int,
    val age: Int,
)