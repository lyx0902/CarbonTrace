package com.example.carbontrace

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.carbontrace.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

    @Composable
    fun AppNavHost(navController: NavHostController) {
        NavHost(navController, startDestination = "welcome") {
            composable("login") { LoginPage(navController) }
            composable("welcome") { WelcomePage(navController) }
            composable("userProfileMaintenance") { UserProfileMaintenancePage(navController) }
            composable("updateResult/{result}") { backStackEntry ->
                val result = backStackEntry.arguments?.getString("result")
            }
        }
    }



    @Composable
    fun WelcomePage(navController: NavHostController) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF1F1))
        ) {
            Image(
                painter = painterResource(id = R.drawable.welcomebackground),
                contentDescription = "welcome_bg",
                modifier = Modifier.fillMaxSize()
            )
            WelcomeContent(navController)
        }
    }

    @Composable
    fun WelcomeContent(navController: NavHostController) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(Modifier.height(72.dp))
            LeafImage()
            Spacer(modifier = Modifier.height(48.dp))
            WelcomeTitle()
            Spacer(modifier = Modifier.height(40.dp))
            WelcomeButtons(navController)
        }
    }

    @Composable
    fun LeafImage() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.leaf),
                contentDescription = "leaf",
                modifier = Modifier.size(300.dp)
            )
        }
    }

    @Composable
    fun WelcomeTitle() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.title),
                contentDescription = "title",
                modifier = Modifier
                    .wrapContentSize()
                    .height(72.dp)
                    .size(600.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "新一代生活健康解决方案",
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    fun WelcomeButtons(navController: NavHostController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "登录",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(onClick = ({ /*TODO*/ })) {
                Text(
                    text = "注册",
                    color = Color(0xFF3A3A3A)
                )
            }
        }
    }

    @Composable
    fun LoginPage(navController: NavHostController) {
        var username by remember { mutableStateOf(user.username) }
        var password by remember { mutableStateOf(user.password) }
        var loginMessage by remember { mutableStateOf("") }
        var userProfileData by remember { mutableStateOf<Result<Map<String, Any>>?>(null) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Spacer(modifier = Modifier.height(65.dp))
                LoginTitle()
                LoginInputBox(
                    username,
                    password,
                    onUsernameChange = {
                        username = it

//                        CoroutineScope(Dispatchers.IO).launch {
//                            val result = UserRepository.getUserByName(it)
//                            withContext(Dispatchers.Main) {
//                                userProfileData = result
//                            }
//                        }

                    },
                    onPasswordChange = { password = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                HintWithUnderLine()
                Spacer(modifier = Modifier.height(16.dp))
                LoginButton(
                    username,
                    password,
                    navController,
                    onLoginMessageChange = { loginMessage = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.navigate("welcome") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 80.dp)
                ) {
                    Text(
                        text = "返回",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }


    @Composable
    fun LoginTitle() {
        Text(
            text = "碳踪寻迹",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 36.sp
        )
    }

    @Composable
    fun LoginInputBox(
        username: String,
        password: String,
        onUsernameChange: (String) -> Unit,
        onPasswordChange: (String) -> Unit
    ) {
        Column {
            Spacer(modifier = Modifier.height(30.dp))
            LoginTextField("Username", username, onUsernameChange)
            Spacer(modifier = Modifier.height(8.dp))
            LoginTextField("Password", password, onPasswordChange)
        }
    }


    @Composable
    fun LoginTextField(placeHolder: String, text: String, onTextChange: (String) -> Unit) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text(text = placeHolder) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            placeholder = {
                Text(text = placeHolder)
            }
        )
    }

    @Composable
    fun HintWithUnderLine() {
        Column(
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
        ) {
            TopText()
            BottomText()
        }
    }


    @Composable
    fun TopText() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "单击下面即表示您同意我们的")
            Text(
                text = "使用条款",
                textDecoration = TextDecoration.Underline
            )
        }
    }

    @Composable
    fun BottomText() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "并同意我们的")
            Text(
                text = "隐私政策",
                textDecoration = TextDecoration.Underline
            )
        }
    }

    @Composable
    fun LoginButton(
        username: String,
        password: String,
        navController: NavHostController,
        onLoginMessageChange: (String) -> Unit
    ) {
        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val result = UserRepository.loginUser(username, password)
                    withContext(Dispatchers.Main) {
                        result.onSuccess {
                            onLoginMessageChange(it)
                            switchScreenType()
                            user.username = username
                            user.password = password
                        }.onFailure {
                            onLoginMessageChange(it.message ?: "Unknown error")
                            navController.navigate("loginResult")
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "登录",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }

    @Composable
    fun UserProfileMaintenancePage(navController: NavHostController) {
        var userProfileData by remember { mutableStateOf<Result<Map<String, Any>>?>(null) }
        var newPassword by remember { mutableStateOf("") }

        LaunchedEffect(user.username) {
            val result = UserRepository.getUserByName(user.username)
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
                label = { Text("newPassword") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

        }
    }
