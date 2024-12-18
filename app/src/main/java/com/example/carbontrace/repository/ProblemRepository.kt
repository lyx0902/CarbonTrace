package com.example.carbontrace.repository

import com.example.carbontrace.api.RetrofitInstance
import com.example.carbontrace.model.AddProblemRequest
import com.example.carbontrace.model.Problem
import org.json.JSONObject

object ProblemRepository {
    suspend fun addProblem(ptext: String, A: String, B: String, C: String, D: String, answer: String): Result<String> {
        val addProblemRequest = AddProblemRequest(ptext, A, B, C, D, answer)
        return try {
            val response = RetrofitInstance.apiService.addProblem(addProblemRequest)
            if (response.isSuccessful) {
                Result.success("Problem added successfully")
            } else {
                Result.failure(Exception("Failed to add problem: ${response.body()?.get("message") ?: "Unknown error"}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Failed to add problem: ${e.message}"))
        }
    }

    suspend fun getProblem(pid: Int): Result<Problem> {
        return try {
            val response = RetrofitInstance.apiService.getProblem(pid)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val problem = Problem(
                        ptext = body["ptext"] ?: "",
                        A = body["A"] ?: "",
                        B = body["B"] ?: "",
                        C = body["C"] ?: "",
                        D = body["D"] ?: "",
                        answer = body["answer"] ?: ""
                    )
                    Result.success(problem)
                } else {
                    Result.failure(Exception("Failed to retrieve problem: Empty response body"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val message = errorBody?.let {
                    JSONObject(it).optString("message", "Failed to retrieve problem: Unknown error")
                } ?: "Failed to retrieve problem: Unknown error"
                Result.failure(Exception(message))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Failed to retrieve problem: ${e.message}"))
        }
    }
}