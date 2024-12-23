package com.example.carbontrace.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carbontrace.model.User
import com.example.carbontrace.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<String>()
    val loginResult: LiveData<String> get() = _loginResult

    private val _registerResult = MutableLiveData<String>()
    val registerResult: LiveData<String> get() = _registerResult

    private val _userProfile = MutableLiveData<User>()
    val userProfile: LiveData<User> get() = _userProfile

    fun clearMessages() {
        _loginResult.value = ""
        _registerResult.value = ""
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = UserRepository.loginUser(username, password)
            result.onSuccess {
                _loginResult.value = it
            }.onFailure {
                _loginResult.value = it.message
            }
        }
    }

    fun register(username: String, password: String, age: Int) {
        viewModelScope.launch {
            val result = UserRepository.registerUser(username, password, age)
            result.onSuccess {
                _registerResult.value = it
            }.onFailure {
                _registerResult.value = it.message
            }
        }
    }

    fun getUserProfile(username: String) {
        viewModelScope.launch {
            try {
                val result = UserRepository.getUserByName(username)
                result.onSuccess { data ->
                    if (data is Map<*, *>) {
                        val uid = (data["uid"] as? Int)
                        val name = (data["username"] as? String) ?: ""
                        val password = (data["password"] as? String) ?: ""
                        val grade = (data["grade"] as? Int) ?: 0
                        val carbons = (data["carbons"] as? Int) ?: 0
                        val points = (data["points"] as? Int) ?: 0
                        val age = (data["age"] as? Int) ?: 0

                        _userProfile.value = User(uid, name, password, grade, carbons, points, age)
                    } else {
                        _userProfile.value = User(null, "Invalid Data", "", 0, 0, 0, 0)
                    }
                }.onFailure {
                    _userProfile.value = User(null, "Bob", "123456", 0, 0, 0, 0)
                }
            } catch (e: Exception) {
                _userProfile.value = User(null, "Error Occurred", "", 0, 0, 0, 0)
                e.printStackTrace()
            }
        }
    }

    fun updateUserProfile(username: String, newUsername: String, newPassword: String) {
        viewModelScope.launch {
            UserRepository.updateProfile(username, newUsername, newPassword)
        }
    }
}