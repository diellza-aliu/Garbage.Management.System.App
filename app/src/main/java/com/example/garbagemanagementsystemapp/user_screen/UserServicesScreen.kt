package com.example.garbagemanagementsystemapp

import android.content.Intent
import android.provider.Settings
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.garbagemanagementsystemapp.data_classes.MenuItem
import com.example.garbagemanagementsystemapp.data_classes.UserViewPair
import com.example.garbagemanagementsystemapp.ui.theme.composables.DrawerBody
import com.example.garbagemanagementsystemapp.ui.theme.composables.DrawerHeader
import com.example.garbagemanagementsystemapp.ui.theme.composables.userServicesRecyclerView
import com.example.garbagemanagementsystemapp.user_screen.MyComplaint
import com.example.garbagemanagementsystemapp.user_screen.RegisterComplaintScreen
import com.example.garbagemanagementsystemapp.user_screen.UserServicesViewModel
import com.example.garbagemanagementsystemapp.user_screen.my_profile.MyProfileScreen
import com.example.garbagemanagementsystemapp.user_screen.update_bin.UpdateBinScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
@ExperimentalComposeUiApi
fun UserServicesScreen(
    viewModel: UserServicesViewModel = hiltViewModel(),
    navController: NavController
) {


    var selectedItem = remember { mutableStateOf<MenuItem?>(MenuItem("", "", "", null)) }

    Column(modifier = Modifier.background(Color.White)) {
        val sheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed
        )
        val bottomSheeSscaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = sheetState
        )
        BottomSheetScaffold(
            scaffoldState = bottomSheeSscaffoldState,
            sheetContent = {
                selectedItem.value?.let { selectedItem ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(600.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        when (selectedItem.id) {
                            "Register Complaint" -> RegisterComplaintScreen(navController = navController)
                            "My Complaint" -> MyComplaint(
                                viewModel.myComplaints
                            )
                            "Update bin's status" -> {
                                UpdateBinScreen(
                                    myComplaints = viewModel.myComplaints
                                )
                            }
                            "My profile" -> {
                                MyProfileScreen(myProfileModel = viewModel.userInfo)
                            }
                            // Add other cases for different menu items
                            else -> {
                                // No specific sheet content for other items
                                Text("No sheet content for this item")
                            }
                        }
                    }
                }
            },
            sheetBackgroundColor = Color(229, 229, 229),
            sheetPeekHeight = 0.dp,
        ) {
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
                        DrawerHeader(viewModel.userInfo.fullName, viewModel.userInfo.email)
                    }
                    DrawerBody(
                        items = listOf(
                            MenuItem(
                                id = "Home",
                                title = stringResource(R.string.Home),
                                contentDescription = "Go to home screen",
                                icon = Icons.Default.Home
                            ),
                            MenuItem(
                                id = "Register Complaint",
                                title = stringResource(R.string.Register_Complaint),
                                contentDescription = "Go to settings screen",
                                icon = Icons.Default.Create
                            ),
                            MenuItem(
                                id = "My Complaint",
                                title = stringResource(R.string.My_complaint),
                                contentDescription = "Go to settings screen",
                                icon = Icons.Default.History
                            ),
                            MenuItem(
                                id = "Update bin's status",
                                title = stringResource(R.string.Update_bin),
                                contentDescription = "Go to settings screen",
                                icon = Icons.Default.Update
                            ),
                            MenuItem(
                                id = "Language",
                                title = stringResource(R.string.Language),
                                contentDescription = "Language",
                                icon = Icons.Default.Language
                            ),
                            MenuItem(
                                id = "Logout",
                                title = stringResource(R.string.Logout),
                                contentDescription = "Logout",
                                icon = Icons.Default.Logout
                            ),
                        ),
                        onItemClick = {
                            when (it.id) {
                                "home" -> {
                                    scope.launch {
                                        scaffoldState.drawerState.close()
                                    }
                                }
                                "Register Complaint" -> {
                                    selectedItem.value = MenuItem(
                                        id = "Register Complaint",
                                        title = "Register Complaint",
                                        contentDescription = "Go to settings screen",
                                        icon = Icons.Default.Create
                                    )
                                    scope.launch {
                                        if (sheetState.isCollapsed) {
                                            sheetState.expand()
                                        } else {
                                            sheetState.collapse()
                                        }
                                        scaffoldState.drawerState.close()
                                    }
                                }
                                "My Complaint" -> {
                                    selectedItem.value = MenuItem(
                                        id = "My Complaint",
                                        title = "My Complaint",
                                        contentDescription = "Go to settings screen",
                                        icon = Icons.Default.Create
                                    )
                                    scope.launch {
                                        if (sheetState.isCollapsed) {
                                            sheetState.expand()
                                        } else {
                                            sheetState.collapse()
                                        }
                                        scaffoldState.drawerState.close()
                                    }
                                }
                                "Update bin's status" -> {
                                    selectedItem.value = MenuItem(
                                        id = "Update bin's status",
                                        title = "Update bin's status",
                                        contentDescription = "Go to settings screen",
                                        icon = Icons.Default.Create
                                    )
                                    scope.launch {
                                        if (sheetState.isCollapsed) {
                                            sheetState.expand()
                                        } else {
                                            sheetState.collapse()
                                        }
                                        scaffoldState.drawerState.close()
                                    }
                                }
                                "Language" -> {
                                    val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    ContextCompat.startActivity(
                                        ApplicationFirebaseAuth.context,
                                        intent,
                                        null
                                    )
                                }
                                "My Profile" -> {
                                    selectedItem.value = MenuItem(
                                        id = "My profile",
                                        title = "My profile",
                                        contentDescription = "Go to settings screen",
                                        icon = Icons.Default.Create
                                    )
                                    scope.launch {
                                        if (sheetState.isCollapsed) {
                                            sheetState.expand()
                                        } else {
                                            sheetState.collapse()
                                        }
                                        scaffoldState.drawerState.close()
                                    }
                                }
                                "Logout" -> viewModel.signOut()
                                else -> selectedItem.value = it
                            }
                        }
                    )
                },
                content = { padding ->
                    Column {
                        headerView()
                        Column(
                            modifier = Modifier
                                .padding(0.dp, 20.dp)
                        ) {
                            userServicesRecyclerView(listOf(
                                UserViewPair(
                                    stringResource(R.string.Register_Complaint),
                                    painterResource(id = R.drawable.register_complaint)
                                ),
                                UserViewPair(
                                    stringResource(R.string.My_complaint),
                                    painterResource(id = R.drawable.my_complaint)
                                ),
                                UserViewPair(
                                    stringResource(R.string.Update_bin),
                                    painterResource(id = R.drawable.update_bin)
                                ),
                                UserViewPair(
                                    stringResource(R.string.My_profile),
                                    painterResource(id = R.drawable.my_profile)
                                )
                            ), onItemClick = {
                                when (it.name) {
                                    "Register Complaint", "Regjistro ankesën" -> {
                                        selectedItem.value = MenuItem(
                                            id = "Register Complaint",
                                            title = "Register Complaint",
                                            contentDescription = "Go to settings screen",
                                            icon = Icons.Default.Create
                                        )
                                        scope.launch {
                                            if (sheetState.isCollapsed) {
                                                sheetState.expand()
                                            } else {
                                                sheetState.collapse()
                                            }
                                        }
                                    }
                                    "My Complaint", "Ankesa ime" -> {
                                        selectedItem.value = MenuItem(
                                            id = "My Complaint",
                                            title = "My Complaint",
                                            contentDescription = "Go to settings screen",
                                            icon = Icons.Default.History
                                        )
                                        scope.launch {
                                            if (sheetState.isCollapsed) {
                                                sheetState.expand()
                                            } else {
                                                sheetState.collapse()
                                            }
                                        }
                                    }
                                    "Update bin's status", "Përditëso statusin e koshit të plehrave" -> {
                                        selectedItem.value = MenuItem(
                                            id = "Update bin's status",
                                            title = "Update bin's status",
                                            contentDescription = "Go to settings screen",
                                            icon = Icons.Default.Update
                                        )
                                        scope.launch {
                                            if (sheetState.isCollapsed) {
                                                sheetState.expand()
                                            } else {
                                                sheetState.collapse()
                                            }
                                        }
                                    }
                                    "My profile", "Profili im" -> {
                                        selectedItem.value = MenuItem(
                                            id = "My profile",
                                            title = "My profile",
                                            contentDescription = "Go to settings screen",
                                            icon = Icons.Default.Home
                                        )
                                        scope.launch {
                                            if (sheetState.isCollapsed) {
                                                sheetState.expand()
                                            } else {
                                                sheetState.collapse()
                                            }
                                        }
                                    }
                                }
                            })

                        }
                    }

                }
            )

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun stickyHeader() {
    LazyColumn {
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
            Text(
                text = stringResource(R.string.Welcome),
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



