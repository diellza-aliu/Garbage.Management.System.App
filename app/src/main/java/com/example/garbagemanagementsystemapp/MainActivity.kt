package com.example.garbagemanagementsystemapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garbagemanagementsystemapp.login_screen.LoginFormActivity
import com.example.garbagemanagementsystemapp.navigation.NavigationGraph
import com.example.garbagemanagementsystemapp.register_screen.RegisterFormActivity
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalFoundationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationGraph()
            Column(
                modifier = Modifier.background(Color.White)
            ){
                stickyHeader()
                mainContent()
                faqFooter()
            }
        }
    }
    @Composable
    fun stickyHeader(){
        LazyColumn{
            stickyHeader {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .size(300.dp)
                        .background(Color.White)
                )
            }
        }
    }
    @Composable
    fun mainContent(){
        Column (
            modifier = Modifier
                .padding(20.dp, 0.dp, 20.dp, 20.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(Color(229, 229, 229))
        ){
            Row {
                Column(
                    modifier = Modifier.clickable {
                        val navigate = Intent(this@MainActivity, LoginFormActivity::class.java)
                        startActivity(navigate)
                    }
                ) {
                    defineImage(R.drawable.login_foreground, "Login", 50.dp, 40.dp, 0.dp, 10.dp)
                    defineText("Login", 75.dp, 0.dp, 0.dp, 0.dp, 0.5f)
                }
                Column(
                    modifier = Modifier.clickable {
                        val navigate = Intent(this@MainActivity, RegisterFormActivity::class.java)
                        startActivity(navigate)
                    }
                ) {
                    defineImage(R.drawable.register_foreground, "Register", 30.dp, 40.dp, 0.dp, 10.dp)
                    defineText("Register", 40.dp, 0.dp, 0.dp, 0.dp, 1f)
                }
            }
            Row {
                Column(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 50.dp)
                        .clickable {
                            val navigate = Intent(this@MainActivity, AdminLoginActivity::class.java)
                            startActivity(navigate)
                        }
                ) {
                    defineImage(R.drawable.admin_foreground, "Admin Login", 50.dp, 40.dp, 0.dp, 10.dp)
                    defineText("Admin Login", 45.dp, 0.dp, 0.dp, 0.dp, 0.5f)
                }
                Column(
                    Modifier.clickable {
                    val navigate = Intent(this@MainActivity, DriverLogin::class.java)
                    startActivity(navigate)
                }
                ) {
                    defineImage(R.mipmap.driver_foreground, "Driver Login", 30.dp, 40.dp, 0.dp, 10.dp)
                    defineText("Driver Login", 15.dp, 0.dp, 0.dp, 0.dp, 1f)
                }
            }
        }
    }
    @Composable
    fun faqFooter(){
        Column (
            modifier = Modifier.background(Color.White)
        ){
            Image(painter = painterResource(id = R.drawable.faq_foreground),
                contentDescription = "Driver Register",
                modifier = Modifier
                    .padding(330.dp, 0.dp, 0.dp, 10.dp)
                    .clip(CircleShape)
                    .size(60.dp)
                    .background(Color(112, 145, 98))
            )
        }
    }

    @Composable
    fun defineImage(id: Int, contentDescription: String, start: Dp, top: Dp, end: Dp, bottom: Dp){
        Image(painter = painterResource(id),
            contentDescription = contentDescription,
            modifier = Modifier
                .padding(start, top, end, bottom)
                .clip(CircleShape)
                .border(BorderStroke(3.dp, Color(112, 145, 98)), CircleShape)
                .size(105.dp)
                .background(Color.White)
        )
    }

    @Composable
    fun defineText(text: String, start: Dp, top: Dp, end: Dp, bottom: Dp, fraction: Float){
        Text(text = text,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth(fraction)
                .clickable {
                    val navigate = Intent(this@MainActivity, RegisterFormActivity::class.java)
                    startActivity(navigate)
                }
                .padding(start, top, end, bottom))
    }
}
