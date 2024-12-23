package com.example.carbontrace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carbontrace.Home.HomeScreen
import com.example.carbontrace.article.AQScreen
import com.example.carbontrace.article.Post
import com.example.carbontrace.article.post1
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
var screenType by mutableStateOf(ScreenType.HOME)
var targetPost by mutableStateOf(post1)

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
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is the Settings Screen")
        Button(onClick = { switchScreenType() }) {
            Text(text = "Go to Home")
        }
    }
}

@Composable
fun HomePage(){
    val homeListLazyListState = rememberLazyListState()

    when (screenType) {
        ScreenType.LOGIN -> {

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