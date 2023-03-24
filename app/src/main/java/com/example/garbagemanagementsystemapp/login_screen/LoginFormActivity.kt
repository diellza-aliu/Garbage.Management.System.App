package com.example.garbagemanagementsystemapp.login_screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.R
import com.example.garbagemanagementsystemapp.register_screen.RegisterFormActivity
import com.example.garbagemanagementsystemapp.UserServicesActivity
import com.example.garbagemanagementsystemapp.register_screen.RegisterViewModel
import com.example.garbagemanagementsystemapp.ui.theme.composables.CustomOutlinedTextField
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            showForm(LocalContext.current)
        }
    }

    @Composable
    fun showForm(context: Context, viewModel: LoginViewModel = hiltViewModel()){
        val focusManager = LocalFocusManager.current
        val scrollState = rememberScrollState()
        val scope = rememberCoroutineScope()
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        var validateEmail by rememberSaveable { mutableStateOf(true) }
        var validatePassword by rememberSaveable { mutableStateOf(true) }
        var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

        var validateEmailError = "The format of the email doesn't seem right"
        var validatePasswordError  = "Must mix capital and non-capital letters, a number, special character and a minimum length of 8"

        fun validateData(email : String, password : String) : Boolean {
            val passwordRegex =  "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&*+=]).{8,}".toRegex()

            validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
            validatePassword = passwordRegex.matches(password)

            return validateEmail && validatePassword
        }

        fun login (email: String, password: String){
            if(validateData(email, password)){
                Toast.makeText(context, "Successfully logged in!", Toast.LENGTH_LONG).show()
                scope.launch {
                    viewModel.loginUser(email, password)
                }
                val intent = Intent(this@LoginFormActivity, UserServicesActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(context, "Please, review fields!!!", Toast.LENGTH_LONG).show()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login to our app",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(vertical = 20.dp),
                color = Color(112, 145, 98)
            )
            Image(painter = painterResource(id = R.drawable.login_logo_foreground),
                contentDescription = "Login Logo",
                modifier = Modifier
                    .size(180.dp)
            )

            CustomOutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                showError = !validateEmail,
                errorMessage = validateEmailError,
                leadingIconImageVector = Icons.Default.PermIdentity,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down)}
                )
            )

            CustomOutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                showError = !validatePassword,
                errorMessage = validatePasswordError,
                isPasswordField = true,
                iSPasswordVisible = isPasswordVisible,
                onVisibilityChange = { isPasswordVisible = it},
                leadingIconImageVector = Icons.Default.PermIdentity,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down)}
                )
            )

            Button(
                onClick = {
                    login(email, password)
                },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(112, 145, 98), contentColor = Color.White)
            ){
                Text(text = "Login", fontSize = 20.sp)
            }
            Text(
                text = "Don't you have an account? Register",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        val intent =
                            Intent(this@LoginFormActivity, RegisterFormActivity::class.java)
                        startActivity(intent)
                    },
                fontSize = 14.sp,
                color = Color.Gray,
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline,
            )
        }
    }
}