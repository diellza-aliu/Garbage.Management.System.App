package com.example.garbagemanagementsystemapp.ui.theme.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.garbagemanagementsystemapp.data_classes.UserViewPair

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: ((String) -> Unit),
    label: String = "",
    leadingIconImageVector: ImageVector,
    leadingIconDescription: String = "",
    isPasswordField: Boolean = false,
    iSPasswordVisible: Boolean = false,
    onVisibilityChange: (Boolean) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    showError: Boolean = false,
    errorMessage: String = ""
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            label = { Text(label)},
            leadingIcon = {
                Icon(
                    imageVector = leadingIconImageVector,
                    contentDescription = leadingIconDescription,
                    tint = if (showError) MaterialTheme.colors.error else MaterialTheme.colors.onSurface
                )
            },
            isError = showError,
            trailingIcon = {
                if (showError && !isPasswordField) Icon(imageVector = Icons.Filled.Error, contentDescription = "Show error icon")
                if(isPasswordField){
                    IconButton(onClick = { onVisibilityChange(!iSPasswordVisible)}) {
                        Icon(
                            imageVector = if (iSPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                }
            },
            visualTransformation = when {
                isPasswordField && iSPasswordVisible -> VisualTransformation.None
                isPasswordField -> PasswordVisualTransformation()
                else -> VisualTransformation.None
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true
        )
        if (showError){
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .offset(y = (-8).dp)
                    .fillMaxWidth(0.9f)
            )
        }
    }
}

@Composable
fun CustomTextField(value: String, onValueChange: ((String) -> Unit)){
    TextField(
        value = value,
        onValueChange = { newValue -> onValueChange(newValue) },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(
                width = 3.dp,
                brush = Brush.horizontalGradient(listOf(Color(112, 145, 98), Color(151, 175, 140))),
                shape = RoundedCornerShape(12.dp)
            ),
        textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}

@Composable
fun EnumTextField(
    enumValues: List<String>,
    onValueSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }

    Column {
        TextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(112, 145, 98),
                            Color(151, 175, 140)
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(onClick = { expanded = true }),
            textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            readOnly = true,
            singleLine = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            enumValues.forEach { value ->
                DropdownMenuItem(onClick = {
                    textFieldValue = value
                    onValueSelected(value)
                    expanded = false
                }) {
                    Text(text = value)
                }
            }
        }
    }
}

@Composable
fun dropDownMenu(list : List<String>): String {
    var expanded by remember{ mutableStateOf(false)}
    var selectedItem by remember {
        mutableStateOf("")
    }
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }
    
    Column() {
        TextField(
            value = selectedItem,
            onValueChange = { selectedItem = it },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .border(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(112, 145, 98),
                            Color(151, 175, 140)
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                ),
            trailingIcon = {
                Icon(icon, "", Modifier.clickable { expanded = !expanded })
            },
            textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            readOnly = true,
            singleLine = true
        )
        
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        modifier = Modifier.width(with(LocalDensity.current){textFieldSize.width.toDp()})) {
            list.forEach { label->
                DropdownMenuItem(onClick = {
                    selectedItem = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
        
    }
    return selectedItem
}

@Composable
fun userServicesRecyclerView(parameterList: List<UserViewPair>, onItemClick: ((UserViewPair) -> Unit)?){

    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = parameterList,) { parameter->
            Surface(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(229, 229, 229))
                ) {

                    Row {

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    onItemClick?.invoke(parameter)
                                }
                        ) {
                            Text(
                                text = parameter.name, style = MaterialTheme.typography.h6.copy(
                                    fontWeight = FontWeight.W400),
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(30.dp, 25.dp, 0.dp, 0.dp),
                                color = Color.Black

                            )
                        }
                        Image(painter = parameter.icon,
                            contentDescription = "Login Logo",
                            modifier = Modifier
                                .size(80.dp)
                                .padding(0.dp, 10.dp, 0.dp, 10.dp),
                            alignment = Alignment.Center
                        )
                    }
                }

            }
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


