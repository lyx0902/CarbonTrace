package com.example.carbontrace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carbontrace.ui.theme.CarbonTraceTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.carbontrace.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarbonTraceTheme{
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var loginMessage by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "welcome") {
        composable("login") { LoginPage(navController) }
        composable("welcome") { WelcomePage(navController) }
        composable("loginResult") { LoginResultPage(loginMessage) }
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
    Image(
        painter = painterResource(id = R.drawable.welcomeleaf),
        contentDescription = "leaf",
        modifier = Modifier
            .wrapContentSize()
            .size(300.dp)
            .padding(start = 125.dp)
    )
}

@Composable
fun WelcomeTitle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.welcomebloom),
            contentDescription = "welcome_bloom",
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
                text = "Beautiful home garden solutions",
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
            onClick = ({ /*TODO*/ }),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Create account",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        TextButton(onClick = { navController.navigate("login") }) {
            Text(
                text = "Log in",
                color = Color(0xFF3A3A3A)
            )
        }
    }
}

@Composable
fun LoginPage(navController: NavHostController) {//登录界面
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginMessage by remember { mutableStateOf("") }

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
            LoginInputBox(username, password, onUsernameChange = { username = it }, onPasswordChange = { password = it })
            Spacer(modifier = Modifier.height(16.dp))
            HintWithUnderLine()
            Spacer(modifier = Modifier.height(16.dp))
            LoginButton(username, password, navController, onLoginMessageChange = { loginMessage = it })
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate("welcome") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 80.dp)
            ) {
                Text(
                    text = "Return",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun LoginResultPage(loginMessage: String) {//登录结果界面
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = loginMessage,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoginTitle(){
    Text(
        text = "CarbonTrace",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 36.sp
    )
}

@Composable
fun LoginInputBox(username: String, password: String, onUsernameChange: (String) -> Unit, onPasswordChange: (String) -> Unit) {
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
fun HintWithUnderLine(){
    Column(
        modifier = Modifier
            .paddingFromBaseline(top = 24.dp,bottom = 16.dp)
    ){
        TopText()
        BottomText()
    }
}

@Composable
fun TopText(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        var keywordPre = "By Clicking below you agree to our ".split(" ")
        for(word in keywordPre){
            Text(text = word)
        }
        Text(
            text = "Terms of Use",
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun BottomText(){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(text = " and consent to Our ")
        Text(
            text = "Privacy Policy",
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun LoginButton(username: String, password: String, navController: NavHostController, onLoginMessageChange: (String) -> Unit) {
    Button(
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val result = UserRepository.loginUser(username, password)
                    withContext(Dispatchers.Main) {
                    result.onSuccess {
                        onLoginMessageChange(it)
                        navController.navigate("loginResult")
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
            text = "Log in",
            color = Color.White,
            fontSize = 20.sp
        )
    }
}

@Composable
fun UserProfilePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(44.dp))
        Text(
            text = "我的",
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(32.dp)
        )
//        Divider(color = Color.LightGray, thickness = 8.dp, modifier = Modifier.fillMaxWidth())
        Text(
            text = "Username :",
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(24.dp)
        )
//        Divider(color = Color.LightGray, thickness = 3.dp, modifier = Modifier.fillMaxWidth())
        Text(
            text = "Grade :",
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(24.dp)
        )
//        Divider(color = Color.LightGray, thickness = 3.dp, modifier = Modifier.fillMaxWidth())
        Text(
            text = "Points :",
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(24.dp)
        )
//        Divider(color = Color.LightGray, thickness = 3.dp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text(
                text = "个人信息维护",
                fontSize = 20.sp
            )
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CarbonTraceTheme{
//        LoginPage(navController = rememberNavController())
//    }
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarbonTraceTheme{
        UserProfilePage()
    }
}