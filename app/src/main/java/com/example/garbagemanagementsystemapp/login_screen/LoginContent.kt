package com.example.garbagemanagementsystemapp.login_screen

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garbagemanagementsystemapp.ApplicationFirebaseAuth
import com.example.garbagemanagementsystemapp.R
import com.example.garbagemanagementsystemapp.ui.theme.composables.CustomOutlinedTextField

@Composable
fun LoginContent(
    logIn: (email: String, password: String) -> Unit,
    navigateToSignUpScreen: () -> Unit){

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val validateEmailError = stringResource(R.string.Email_error)
    val validatePasswordError  = stringResource(R.string.Password_error)

    fun validateData(email : String, password : String) : Boolean {
        val passwordRegex =  "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&*+=]).{8,}".toRegex()

        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePassword = passwordRegex.matches(password)

        return validateEmail && validatePassword
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.Login_to_app),
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
            label = stringResource(R.string.Email),
            showError = !validateEmail,
            errorMessage = validateEmailError,
            leadingIconImageVector = Icons.Default.PermIdentity,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
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
            label = stringResource(R.string.Password),
            showError = !validatePassword,
            errorMessage = validatePasswordError,
            isPasswordField = true,
            iSPasswordVisible = isPasswordVisible,
            onVisibilityChange = { isPasswordVisible = it},
            leadingIconImageVector = Icons.Default.PermIdentity,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down)}
            )
        )

        Button(
            onClick = {
                if(validateData(email, password)){
                    logIn(email, password)
                }else{
                    Toast.makeText(ApplicationFirebaseAuth.context, "Something went wrong. Invalid email or password!", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(112, 145, 98), contentColor = Color.White)
        ){
            Text(text = stringResource(R.string.Login), fontSize = 20.sp)
        }
        Text(
            text = stringResource(R.string.Already_have_account),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    navigateToSignUpScreen()
                },
            fontSize = 14.sp,
            color = Color.Gray,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
        )
    }
}