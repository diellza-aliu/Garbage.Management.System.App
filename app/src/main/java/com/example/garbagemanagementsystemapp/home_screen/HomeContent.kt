package com.example.garbagemanagementsystemapp.home_screen

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.garbagemanagementsystemapp.ApplicationFirebaseAuth
import com.example.garbagemanagementsystemapp.R


@Composable
fun HomeContent(
    navigateToSignUpScreen: () -> Unit,
    navigateToSignInScreen: () -> Unit,
    navigateToAdminLoginScreen: () -> Unit,
    navigateToDriverLoginScreen: () -> Unit
){
    Column(
        modifier = Modifier.background(Color.White)
    ){
        stickyHeader()
        mainContent(navigateToSignInScreen, navigateToSignUpScreen, navigateToAdminLoginScreen, navigateToDriverLoginScreen)
        faqFooter()
    }
}

@OptIn(ExperimentalFoundationApi::class)
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
fun mainContent(navigateToSignInScreen: () -> Unit, navigateToSignUpScreen: () -> Unit, navigateToAdminLoginScreen: () -> Unit, navigateToDriverLoginScreen: () -> Unit) {
    Column (
        modifier = Modifier
            .padding(20.dp, 0.dp, 20.dp, 20.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(Color(229, 229, 229))
    ){
        Row {
            Column(
                modifier = Modifier.clickable {
                    navigateToSignInScreen()
                }
            ) {
                defineImage(R.drawable.login_foreground, "Login", 50.dp, 40.dp, 0.dp, 10.dp)
                defineText(stringResource(R.string.Login), 75.dp, 0.dp, 0.dp, 0.dp, 0.5f)
            }
            Column(
                modifier = Modifier.clickable {
                    navigateToSignUpScreen()
                }
            ) {
                defineImage(R.drawable.register_foreground, "Register", 30.dp, 40.dp, 0.dp, 10.dp)
                defineText(stringResource(R.string.Register), 40.dp, 0.dp, 0.dp, 0.dp, 1f)
            }
        }
        Row {
            Column(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 50.dp)
                    .clickable {
                        navigateToAdminLoginScreen()
                    }
            ) {
                defineImage(R.drawable.admin_foreground, "Admin Login", 50.dp, 40.dp, 0.dp, 10.dp)
                defineText(stringResource(R.string.Admin_login), 45.dp, 0.dp, 0.dp, 0.dp, 0.5f)
            }
            Column(
                Modifier.clickable {
                        navigateToDriverLoginScreen()
                }
            ) {
                defineImage(R.mipmap.driver_foreground, "Driver Login", 30.dp, 40.dp, 0.dp, 10.dp)
                defineText(stringResource(R.string.Driver_login), 15.dp, 0.dp, 0.dp, 0.dp, 1f)
            }
        }
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
            .padding(start, top, end, bottom))
}

@Composable
fun faqFooter(){
    Column (
        modifier = Modifier.background(Color.White)
    ){
        Image(painter = painterResource(id = R.drawable.ic_baseline_language_24),
            contentDescription = "Driver Register",
            modifier = Modifier
                .padding(330.dp, 0.dp, 0.dp, 10.dp)
                .clip(CircleShape)
                .size(60.dp)
                .background(Color(112, 145, 98))
                .clickable {
                    val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(ApplicationFirebaseAuth.context, intent, null)
                }
        )
        Image(painter = painterResource(id = R.drawable.weather_foreground),
            contentDescription = "Driver Register",
            modifier = Modifier
                .padding(280.dp, 0.dp, 0.dp, 10.dp)
                .clip(CircleShape)
                .size(60.dp)
                .background(Color(112, 145, 98))
                .clickable {
                    val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(ApplicationFirebaseAuth.context, intent, null)
                }
        )
    }
}