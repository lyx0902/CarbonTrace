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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carbontrace.ui.theme.CarbonTraceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarbonTraceTheme{
                Surface(modifier = Modifier.fillMaxSize()) {
                    WelcomePage()
                }
            }
        }
    }
}

@Composable
fun WelcomePage() {
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
        WelcomeContent()
    }
}

@Composable
fun WelcomeContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(72.dp))
        LeafImage()
        Spacer(modifier = Modifier.height(48.dp))
        WelcomeTitle()
        Spacer(modifier = Modifier.height(40.dp))
        WelcomeButtons()
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
fun WelcomeButtons() {
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
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "Log in",
                color = Color(0xFF3A3A3A)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarbonTraceTheme{
        WelcomePage()
    }
}