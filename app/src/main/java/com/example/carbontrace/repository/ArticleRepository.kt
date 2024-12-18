package com.example.carbontrace.repository

import com.example.carbontrace.api.RetrofitInstance
import com.example.carbontrace.model.AddArticleRequest
import org.json.JSONObject

object ArticleRepository {
    suspend fun addArticle(atext: String): Result<String> {
        val addArticleRequest = AddArticleRequest(atext)
        return try {
            val response = RetrofitInstance.apiService.addArticle(addArticleRequest)
            if (response.isSuccessful) {
                Result.success("添加成功")
            } else {
                Result.failure(Exception("添加失败: ${response.body()?.get("message") ?: "未知错误"}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("添加失败: ${e.message}"))
        }
    }

    suspend fun getArticle(aid: Int): Result<String> {
        return try {
            val response = RetrofitInstance.apiService.getArticle(aid)
            if (response.isSuccessful) {
                val atext = response.body()?.get("atext") ?: "获取成功"
                Result.success(atext)
            } else {
                val errorBody = response.errorBody()?.string()
                val message = errorBody?.let {
                    JSONObject(it).optString("message", "获取失败: 未知错误")
                } ?: "获取失败: 未知错误"
                Result.failure(Exception(message))
            }
        } catch (e: Exception) {
            Result.failure(Exception("获取失败: ${e.message}"))
        }
    }
}