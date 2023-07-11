package com.example.garbagemanagementsystemapp.ui.theme.composables


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garbagemanagementsystemapp.R
import com.example.garbagemanagementsystemapp.data_classes.MenuItem

@Composable
fun DrawerHeader(userFullName: String, email: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp, start = 20.dp)
            .background(Color(229, 229, 229)),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
        ) {
            val drawable =
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "ProfilePic"
                )
            Text(
                text = userFullName,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 30.dp)
            )
            Text(
                text = email,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Light,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 30.dp)
            )
        }
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                if (item.icon != null) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}