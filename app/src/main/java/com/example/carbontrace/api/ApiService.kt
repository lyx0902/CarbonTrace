package com.example.carbontrace.api

import com.example.carbontrace.model.AddArticleRequest
import com.example.carbontrace.model.AddPointsRequest
import com.example.carbontrace.model.AddProblemRequest
import com.example.carbontrace.model.LoginRequest
import com.example.carbontrace.model.RegisterRequest
import com.example.carbontrace.model.UpdateProfileRequest
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

    @PUT("/update_profile")
    suspend fun updateProfile(@Body updateProfileRequest: UpdateProfileRequest): Response<Map<String, String>>

    @GET("/get_user_by_name")
    suspend fun getUserByName(@Query("name") name: String): Response<Map<String, Any>>

    @POST("/add_article")
    suspend fun addArticle(@Body addArticleRequest: AddArticleRequest): Response<Map<String, String>>

    @GET("/get_article")
    suspend fun getArticle(@Query("aid") aid: Int): Response<Map<String, String>>

    @POST("/add_problem")
    suspend fun addProblem(@Body addProblemRequest: AddProblemRequest): Response<Map<String, String>>

    @GET("/get_problem")
    suspend fun getProblem(@Query("pid") pid: Int): Response<Map<String, String>>

    @PUT("/add_points")
    suspend fun addPoints(@Body addPointsRequest: AddPointsRequest): Response<Map<String, String>>
}