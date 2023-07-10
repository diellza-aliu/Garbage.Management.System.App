package com.example.garbagemanagementsystemapp.user_screen.my_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyProfile(
    myProfileModel: MyProfileModel,
    updateProfile: (name : String, fullName : String, email : String) -> Unit
) {
    var name by remember { mutableStateOf(myProfileModel.name) }
    var fullName by remember { mutableStateOf(myProfileModel.fullName) }
    var email by remember { mutableStateOf(myProfileModel.email) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "My Profile",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        EditableProfileField(
            label = "Name",
            value = name,
            onValueChange = { newName ->
                name = newName
            },
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color(240, 240, 240),
            textColor = Color.Black
        )

        EditableProfileField(
            label = "Full Name",
            value = fullName,
            onValueChange = { newFullName ->
                fullName = newFullName
            },
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color(240, 240, 240),
            textColor = Color.Black
        )

        EditableProfileField(
            label = "Email",
            value = email,
            onValueChange = { newEmail ->
                email = newEmail
            },
            keyboardType = KeyboardType.Email,
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color(240, 240, 240),
            textColor = Color.Black
        )

        Button(
            onClick = {
                updateProfile(name, fullName, email)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(112, 145, 98)),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "Save Profile",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun EditableProfileField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    shape: Shape = TextFieldDefaults.TextFieldShape,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "$label:",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
                .padding(start = 8.dp, top = 8.dp)
                .background(backgroundColor, shape)
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .height(100.dp)
                    .padding(horizontal = 8.dp, 0.dp),
                textStyle = TextStyle(color = textColor),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = textColor,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
            )

            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = textColor,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(24.dp)
            )
        }
    }
}
