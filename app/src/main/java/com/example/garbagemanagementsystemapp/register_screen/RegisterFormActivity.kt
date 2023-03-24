package com.example.garbagemanagementsystemapp.register_screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.UserServicesActivity
import com.example.garbagemanagementsystemapp.login_screen.LoginFormActivity
import com.example.garbagemanagementsystemapp.ui.theme.composables.CustomOutlinedTextField
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFormActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            showForm(LocalContext.current)
        }
    }

@Composable
    fun showForm(context: Context, viewModel: RegisterViewModel = hiltViewModel()){
        val focusManager = LocalFocusManager.current
        val scrollState = rememberScrollState()
        val scope = rememberCoroutineScope()
        var name by rememberSaveable { mutableStateOf("") }
        var surname by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable { mutableStateOf("") }
        var phone by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var confirmPassword by rememberSaveable { mutableStateOf("") }

        var validateName by rememberSaveable { mutableStateOf(true) }
        var validateSurname by rememberSaveable { mutableStateOf(true) }
        var validateEmail by rememberSaveable { mutableStateOf(true) }
        var validatePhone by rememberSaveable { mutableStateOf(true) }
        var validatePassword by rememberSaveable { mutableStateOf(true) }
        var validateConfirmPassword by rememberSaveable { mutableStateOf(true) }
        var validateArePasswordsEqual by rememberSaveable { mutableStateOf(true) }
        var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
        var isConfirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

        var validateNameError = "Please input a valid name!"
        var validateSurnameError = "Please input a valid surname!"
        var validateEmailError = "The format of the email doesn't seem right"
        var validatePhoneError = "The format of the phone number doesn't seem right"
        var validatePasswordError  = "Must mix capital and non-capital letters, a number, special character and a minimum length of 8"
        var validateEqualPasswordError = "Passwords must be equal"

        fun validateData(name : String, surname : String, email : String, phone : String, password : String, confirmPassword : String) : Boolean {
            val passwordRegex =  "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&*+=]).{8,}".toRegex()

            validateName = name.isNotBlank()
            validateSurname = surname.isNotBlank()
            validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
            validatePhone = Patterns.PHONE.matcher(phone).matches()
            validatePassword = passwordRegex.matches(password)
            validateArePasswordsEqual = password == confirmPassword

            return validateName && validateSurname && validateEmail && validatePhone && validatePassword && validateConfirmPassword && validateArePasswordsEqual
        }

        fun register (
            name: String,
            surname: String,
            email: String,
            phone: String,
            password: String,
            confirmPassword: String
        ){
            if(validateData(name, surname, email, phone, password, confirmPassword)){
                Toast.makeText(context, "Successfully registered!", Toast.LENGTH_LONG).show()
                scope.launch {
                    viewModel.registerUser(email, password)
                }
                val intent = Intent(this@RegisterFormActivity, UserServicesActivity::class.java)
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
                text = "Register to our app",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(vertical = 20.dp),
                color = Color(112, 145, 98)
            )

            CustomOutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = "Name",
                showError = !validateName,
                errorMessage = validateNameError,
                leadingIconImageVector = Icons.Default.PermIdentity,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down)}
                )
            )

            CustomOutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = "Surname",
                showError = !validateSurname,
                errorMessage = validateSurnameError,
                leadingIconImageVector = Icons.Default.PermIdentity,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down)}
                )
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
                value = phone,
                onValueChange = { phone = it },
                label = "Phone",
                showError = !validatePhone,
                errorMessage = validatePhoneError,
                leadingIconImageVector = Icons.Default.PermIdentity,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
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

            CustomOutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirm Password",
                showError = !validateConfirmPassword || !validateArePasswordsEqual,
                errorMessage = if(!validateConfirmPassword) validatePasswordError else validateEqualPasswordError,
                isPasswordField = true,
                iSPasswordVisible = isConfirmPasswordVisible,
                onVisibilityChange = { isConfirmPasswordVisible = it},
                leadingIconImageVector = Icons.Default.Password,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus()}
                )
            )

            Button(
                onClick = {
                    register(name, surname, email, phone, password, confirmPassword)
                },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(112, 145, 98), contentColor = Color.White)
            ){
                Text(text = "Register", fontSize = 20.sp)
            }
            Text(
                text = "Already have an account? Login",
                modifier = Modifier
                    .align(CenterHorizontally)
                    .clickable {
                        val intent =
                            Intent(this@RegisterFormActivity, LoginFormActivity::class.java)
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