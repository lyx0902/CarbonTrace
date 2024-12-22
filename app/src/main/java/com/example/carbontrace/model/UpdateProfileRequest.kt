package com.example.carbontrace.model

data class UpdateProfileRequest (
    val username:String,
    val new_name : String,
    val new_password :String,
    val new_age : Int
)