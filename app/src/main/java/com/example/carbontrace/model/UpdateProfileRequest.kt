package com.example.carbontrace.model

data class UpdateProfileRequest (
    val username:String,
    val password : String,
    val new_password :String,
)