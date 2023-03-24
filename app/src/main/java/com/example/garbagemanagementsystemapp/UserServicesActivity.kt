package com.example.garbagemanagementsystemapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garbagemanagementsystemapp.data_classes.MenuItem
import com.example.garbagemanagementsystemapp.data_classes.UserViewPair
import com.example.garbagemanagementsystemapp.ui.theme.GarbageManagementSystemAppTheme
import com.example.garbagemanagementsystemapp.ui.theme.composables.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
class UserServicesActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(modifier = Modifier.background(Color.White)) {
                    GarbageManagementSystemAppTheme {
                        val sheetState = rememberBottomSheetState(
                            initialValue = BottomSheetValue.Collapsed
                        )
                        val bottomSheeSscaffoldState = rememberBottomSheetScaffoldState(
                            bottomSheetState = sheetState
                        )
                        BottomSheetScaffold(
                            scaffoldState = bottomSheeSscaffoldState,
                            sheetContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(600.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Bottom sheet",
                                        fontSize = 60.sp
                                    )
                                }
                            },
                            sheetBackgroundColor = Color(229, 229, 229),
                            sheetPeekHeight = 0.dp,
                        ){
                            val scope = rememberCoroutineScope()

                            val scaffoldState = rememberScaffoldState()
                            Scaffold(
                                scaffoldState = scaffoldState,
                                topBar = {
                                    AppBar(
                                        onNavigationIconClick = {
                                            scope.launch {
                                                scaffoldState.drawerState.open()
                                            }
                                        }
                                    )
                                },
                                drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                                drawerContent = {
                                    Column(Modifier.background(Color(229, 229, 229))) {
                                        DrawerHeader()
                                    }
                                    DrawerBody(
                                        items = listOf(
                                            MenuItem(
                                                id = "home",
                                                title = "Home",
                                                contentDescription = "Go to home screen",
                                                icon = Icons.Default.Home
                                            ),
                                            MenuItem(
                                                id = "Register Complaint",
                                                title = "Register Complaint",
                                                contentDescription = "Go to settings screen",
                                                icon = Icons.Default.Create
                                            ),
                                            MenuItem(
                                                id = "My Complaint",
                                                title = "My Complaint",
                                                contentDescription = "Go to settings screen",
                                                icon = Icons.Default.History
                                            ),
                                            MenuItem(
                                                id = "Update bin's status",
                                                title = "Update bin's status",
                                                contentDescription = "Go to settings screen",
                                                icon = Icons.Default.Update
                                            ),
                                            MenuItem(
                                                id = "Logout",
                                                title = "Logout",
                                                contentDescription = "Logout",
                                                icon = Icons.Default.Logout
                                            ),
                                        ),
                                        onItemClick = {
                                            println("Clicked on ${it.title}")
                                        }
                                    )
                                }
                            ) {
                                Column{
                                    headerView()
                                    Column(modifier = Modifier
                                        .padding(0.dp, 20.dp)
                                    ) {
                                        RecyclerView(listOf(
                                            UserViewPair("Register Complaint", painterResource(id = R.drawable.register_complaint)),
                                            UserViewPair("My Complaint", painterResource(id = R.drawable.my_complaint)),
                                            UserViewPair("Update bin's status", painterResource(id = R.drawable.update_bin)),
                                            UserViewPair("My Profile", painterResource(id = R.drawable.my_profile))
                                        ), onItemClick = {
                                            if(it.name.equals("Register Complaint")){
                                                scope.launch {
                                                    if(sheetState.isCollapsed){
                                                        sheetState.expand()
                                                    }else{
                                                        sheetState.collapse()
                                                    }
                                                }
                                            }
                                        })

                                    }
                                }

                            }
                        }


                    }

            }

        }
    }

    @Composable
    fun headerView() {
        Column(
            modifier = Modifier.background(Color(112, 145, 98))
        ) {
            stickyHeader()
        }
    }

    @Composable
    fun stickyHeader(){
        LazyColumn{
            stickyHeader {
                Image(
                    painter = painterResource(id = R.drawable.logo_transparent),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .padding(40.dp)
                        .fillMaxSize()
                        .size(150.dp)
                        .background(Color(112, 145, 98))
                )
                Text(text = "Welcome to Yo Garbage, where you can manage your environment!",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp, 0.dp, 50.dp, 40.dp)
                        .clickable {

                        },
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }

}


