package com.example.carbontrace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.carbontrace.Home.HomeScreen
import com.example.carbontrace.article.AQScreen
import com.example.carbontrace.article.post1
import com.example.carbontrace.article.post3
import com.example.carbontrace.model.User
import com.example.carbontrace.repository.UserRepository
import com.example.carbontrace.transfer.UserViewModel
import com.example.carbontrace.ui.theme.CarbonTraceTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            CarbonTraceTheme {
                HomePage(navController, userViewModel)
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
fun ProfileScreen(navController: NavHostController, userViewModel: UserViewModel) {
    val userProfileData by userViewModel.userProfile.observeAsState()
    var newPassword by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect("Bob") {
        userViewModel.getUserProfile("Bob")
    }

    Scaffold(
        bottomBar = { BottomNavigation() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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

            userProfileData?.let { user ->
                Text(
                    text = "Username: ${user.username}",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Password: ${user.password}",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            } ?: run {
                Text(
                    text = "Loading...",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("newpassword") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        val result = UserRepository.updateProfile("Bob","123456", newPassword)
                        if (result.isSuccess) {
                            showDialog = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "更新信息")
            }
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "提示") },
                    text = { Text(text = "更新成功") },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                switchScreenType()
                            }
                        ) {
                            Text("确定")
                        }
                    }
                )
            }
        }
    }
}


//底部导航栏，在本页面中只让Home的onclick产生页面切换效果，让Profile的被选中属性为真
@Composable
private fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Home"
                )
            },
            selected = false,
            onClick = { switchScreenType() }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Profile"
                )
            },
            selected = true,
            onClick = { }
        )
    }
}

@Composable
fun HomePage(navController: NavHostController, userViewModel: UserViewModel) {
    val homeListLazyListState = rememberLazyListState()

    when (screenType) {
        ScreenType.LOGIN -> {
            Surface(modifier = Modifier.fillMaxSize()) {
                AppNavHost(navController)
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
                onBack = { switchScreenType() },
            )
        }
        ScreenType.PROFILE -> {
            ProfileScreen(navController, userViewModel)
        }
    }
}
