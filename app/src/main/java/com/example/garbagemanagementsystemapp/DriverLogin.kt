package com.example.garbagemanagementsystemapp

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garbagemanagementsystemapp.ui.theme.composables.CustomOutlinedTextField

class DriverLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            showForm(LocalContext.current)
        }
    }

    @Composable
    fun showForm(context: Context) {
        val focusManager = LocalFocusManager.current
        val scrollState = rememberScrollState()
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        var validateEmail by rememberSaveable { mutableStateOf(true) }
        var validatePassword by rememberSaveable { mutableStateOf(true) }
        var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

        val validateEmailError = "The format of the email doesn't seem right"
        val validatePasswordError =
            "Must mix capital and non-capital letters, a number, special character and a minimum length of 8"

        fun validateData(email: String, password: String): Boolean {
            val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&*+=]).{8,}".toRegex()

            validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
            validatePassword = passwordRegex.matches(password)

            return validateEmail && validatePassword
        }

        fun adminLogin(email: String, password: String) {
            if (validateData(email, password)) {
                Toast.makeText(context, "Successfully logged in!", Toast.LENGTH_LONG).show()
            } else {
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
                text = "Driver Login",
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
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
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
                onVisibilityChange = { isPasswordVisible = it },
                leadingIconImageVector = Icons.Default.PermIdentity,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            Button(
                onClick = {
                    adminLogin(email, password)
                },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(112, 145, 98),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Login", fontSize = 20.sp)
            }
        }
    }
}