package com.example.carbontrace.repository

import com.example.carbontrace.api.RetrofitInstance
import com.example.carbontrace.model.AddPointsRequest
import com.example.carbontrace.model.LoginRequest
import com.example.carbontrace.model.RegisterRequest
import com.example.carbontrace.model.UpdateProfileRequest
import org.json.JSONObject
object UserRepository {
    suspend fun registerUser(username: String, password: String, age:Int): Result<String> {
        val registerRequest = RegisterRequest(username, password,age)
        return try {
            val response = RetrofitInstance.apiService.registerUser(registerRequest)
            if (response.isSuccessful) {
                Result.success("注册成功")
            } else {
                Result.failure(Exception("注册失败: ${response.body()?.get("message") ?: "未知错误"}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("注册失败: ${e.message}"))
        }
    }

    // 用户登录
    suspend fun loginUser(username: String, password: String): Result<String> {
        val loginRequest = LoginRequest(username, password)
        return try {
            val response = RetrofitInstance.apiService.loginUser(loginRequest)
            if (response.isSuccessful) {
                val message = response.body()?.get("message") ?: "登录成功"
                Result.success(message)
            } else {
                val errorBody = response.errorBody()?.string()
                val message = errorBody?.let {
                    // 假设错误主体是一个包含 "message" 字段的 JSON 对象
                    JSONObject(it).optString("message", "登录失败: 未知错误")
                }?: "登录失败: 未知错误"
                Result.failure(Exception(message))
            }
        } catch (e: Exception) {
            Result.failure(Exception("登录失败: ${e.message}"))
        }
    }

    //用户个人信息更新
    suspend fun updateProfile(username: String,newName:String, newPassword: String): Result<String> {
        val updateProfileRequest = UpdateProfileRequest(username, newName,newPassword)
        return try {
            val response = RetrofitInstance.apiService.updateProfile(updateProfileRequest)
            if (response.isSuccessful) {
                Result.success("更新成功")
            } else {
                Result.failure(Exception("更新失败: ${response.body()?.get("message") ?: "未知错误"}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("更新失败: ${e.message}"))
        }
    }

    //用户信息查询
    suspend fun getUserByName(username: String): Result<Map<String, Any>> {
        return try {
            val response = RetrofitInstance.apiService.getUserByName(username)
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyMap())
            } else {
                Result.failure(Exception("查询失败: ${response.body()?.get("message") ?: "未知错误"}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("查询失败: ${e.message}"))
        }
    }

    //用户积分增加
    suspend fun addPoints(username: String, addpoint: Int): Result<String> {
        val addPointsRequest = AddPointsRequest(username, addpoint)
        return try {
            val response = RetrofitInstance.apiService.addPoints(addPointsRequest)
            if (response.isSuccessful) {
                Result.success("分数更新成功")
            } else {
                Result.failure(Exception("分数更新失败: ${response.body()?.get("message") ?: "未知错误"}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("分数更新失败: ${e.message}"))
        }
    }
}