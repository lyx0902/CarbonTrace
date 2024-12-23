package com.example.carbontrace.model

data class User (
    var uid: Int?,
    var username: String,
    var password: String,
    var grade: Int,
    var carbons: Int,
    var points: Int,
    var age: Int,
)