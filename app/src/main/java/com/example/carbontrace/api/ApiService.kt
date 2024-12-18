package com.example.carbontrace.api

import com.example.carbontrace.model.LoginRequest
import com.example.carbontrace.model.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query
interface ApiService {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<Map<String, String>>

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<Map<String, String>>
}