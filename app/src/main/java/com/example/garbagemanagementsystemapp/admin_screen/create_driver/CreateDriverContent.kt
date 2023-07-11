package com.example.garbagemanagementsystemapp.admin_screen.create_driver

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garbagemanagementsystemapp.R
import com.example.garbagemanagementsystemapp.home_screen.defineText
import com.example.garbagemanagementsystemapp.ui.theme.composables.CustomOutlinedTextField
import com.example.garbagemanagementsystemapp.ui.theme.composables.dropDownMenu

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateDriverContent(
    createDriver: (email: String, password: String, fullName: String, name: String, type: String) -> Unit
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var fullName by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var typeList = listOf("1", "2", "3")

    val bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)

    LaunchedEffect(bottomSheetState.isCollapsed) {
        if (bottomSheetState.isCollapsed) {
            email = ""
            password = ""
            fullName = ""
            name = ""
            typeList = listOf("1", "2", "3")
        }
    }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var validateName by rememberSaveable { mutableStateOf(true) }
    val validateFullName by rememberSaveable { mutableStateOf(true) }
    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val validateNameError = stringResource(R.string.Name_error)
    val validateEmailError = stringResource(R.string.Email_error)
    val validatePasswordError = stringResource(R.string.Password_error)

    fun validateData(email: String, password: String, name: String): Boolean {
        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&*+=]).{8,}".toRegex()

        validateName = name.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePassword = passwordRegex.matches(password)

        return validateName && validateFullName && validateEmail && validatePassword
    }

    fun register(email: String, password: String, fullName: String, name: String, type: String) {
        if (validateData(email, password, name)) {
            createDriver(email, password, fullName, name, type)
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
        CustomOutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = stringResource(R.string.Name),
            showError = !validateName,
            errorMessage = validateNameError,
            leadingIconImageVector = Icons.Default.PermIdentity,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
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
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
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
            onVisibilityChange = { isPasswordVisible = it },
            leadingIconImageVector = Icons.Default.PermIdentity,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        defineText(
            text = "Type",
            start = 20.dp,
            top = 8.dp,
            end = 0.dp,
            bottom = 0.dp,
            fraction = 1f
        )
        val type = dropDownMenu(typeList)
        Button(
            onClick = {
                register(email, password, fullName, name, type)
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(112, 145, 98),
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(R.string.Register), fontSize = 20.sp)
        }
    }
}