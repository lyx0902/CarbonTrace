package com.example.carbontrace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.carbontrace.Home.HomeScreen
import com.example.carbontrace.article.AQScreen
import com.example.carbontrace.article.post1
import com.example.carbontrace.model.User
import com.example.carbontrace.repository.UserRepository
import com.example.carbontrace.ui.theme.CarbonTraceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarbonTraceTheme {
                HomePage()
            }
        }
    }
}

//只有登录、主页、文章信息和个人页面的页面状态
enum class ScreenType {
    LOGIN,
    HOME,
    AQDETAILS,
    PROFILE
}

//可变状态
var screenType by mutableStateOf(ScreenType.LOGIN)
var targetPost by mutableStateOf(post1)
var user by mutableStateOf(
    User(
        uid = 1,
        username = "",
        password = "",
        grade = 1,
        carbons = 100,
        points = 200,
        age = 25
    )
)

//调用该函数以切换页面状态（如果加新的状态需要重写该函数）
fun switchScreenType() {
    screenType = when (screenType) {
        ScreenType.LOGIN -> ScreenType.HOME
        ScreenType.HOME -> ScreenType.PROFILE
        ScreenType.AQDETAILS -> ScreenType.HOME
        ScreenType.PROFILE -> ScreenType.HOME
    }
}

//实例个人信息页面
@Composable
fun ProfileScreen() {
    var userProfileData by remember { mutableStateOf<Result<Map<String, Any>>?>(null) }
    var newPassword by remember { mutableStateOf("") }

    LaunchedEffect(user.username) {
        val result = UserRepository.getUserByName("Bob")
        userProfileData = result
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "个人信息维护",
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        userProfileData?.let { result ->
            result.onSuccess { data ->
                data.forEach { (key, value) ->
                    Text(
                        text = "$key: $value",
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }?.onFailure {
                Text(
                    text = "获取用户信息失败: ${it.message}",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("newpassword") },
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun HomePage(){
    val homeListLazyListState = rememberLazyListState()

    when (screenType) {
        ScreenType.LOGIN -> {
            Surface(modifier = Modifier.fillMaxSize()) {
                AppNavHost(rememberNavController())
            }
        }
        ScreenType.HOME -> {
            HomeScreen(
                homeListLazyListState = homeListLazyListState
            )
        }
        ScreenType.AQDETAILS -> {
            AQScreen(
                post = targetPost,
                onBack = {switchScreenType()},
            )
        }
        ScreenType.PROFILE -> {
            ProfileScreen()
        }
    }
}