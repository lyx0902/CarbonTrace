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
    NavHost(navController = navController, startDestination = "welcome") {
        composable("login") { LoginPage(navController) }
        composable("welcome") { WelcomePage(navController) }
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
fun LoginPage(navController: NavHostController){
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ){
        Spacer(modifier = Modifier.height(65.dp))
        LoginTitle()
        LoginInputBox()
        Spacer(modifier = Modifier.height(16.dp))
        HintWithUnderLine()
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton()
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
fun LoginInputBox(){
    Column{
        Spacer(modifier = Modifier.height(30.dp))
        LoginTextField("Username")
        Spacer(modifier = Modifier.height(8.dp))
        LoginTextField("Password")
    }
}

@Composable
fun LoginTextField(placeHolder: String) {
    OutlinedTextField(
        value = "",
        onValueChange = { /*TODO*/ },
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
fun LoginButton(){
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ){
        Text(
            text = "Log in",
            color = Color.White,
            fontSize = 20.sp
        )
    }
}





//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CarbonTraceTheme{
//        WelcomePage()
//    }
//}