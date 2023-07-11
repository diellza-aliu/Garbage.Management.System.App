package com.example.garbagemanagementsystemapp.admin_screen

import android.content.Intent
import android.provider.Settings
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.garbagemanagementsystemapp.ApplicationFirebaseAuth
import com.example.garbagemanagementsystemapp.R
import com.example.garbagemanagementsystemapp.admin_screen.create_driver.CreateDriverScreen
import com.example.garbagemanagementsystemapp.admin_screen.user_details.UserDetailsContent
import com.example.garbagemanagementsystemapp.admin_screen.view_all_drivers.ViewAllDriversScreen
import com.example.garbagemanagementsystemapp.admin_screen.view_work_reports.ViewWorkReportContent
import com.example.garbagemanagementsystemapp.data_classes.MenuItem
import com.example.garbagemanagementsystemapp.data_classes.UserViewPair
import com.example.garbagemanagementsystemapp.headerView
import com.example.garbagemanagementsystemapp.navigation.NavigationGraph
import com.example.garbagemanagementsystemapp.ui.theme.composables.DrawerBody
import com.example.garbagemanagementsystemapp.ui.theme.composables.DrawerHeader
import com.example.garbagemanagementsystemapp.ui.theme.composables.userServicesRecyclerView
import com.example.garbagemanagementsystemapp.user_screen.RegisterComplaintScreen
import com.example.garbagemanagementsystemapp.user_screen.my_profile.MyProfileScreen
import com.example.garbagemanagementsystemapp.user_screen.update_bin.UpdateBinScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
@ExperimentalComposeUiApi
fun AdminScreen(viewModel: AdminScreenViewModel = hiltViewModel()) {

    val navController: NavHostController = rememberAnimatedNavController()
    NavigationGraph(
        navController = navController
    )

    val selectedItem = remember { mutableStateOf<MenuItem?>(MenuItem("", "", "", null)) }

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
                            "Create bin" -> {
                                RegisterComplaintScreen(navController = navController)
                            }
                            "Update bin's status" -> {
                                UpdateBinScreen(myComplaints = viewModel.myComplaints)
                            }
                            "Create driver" -> {
                                CreateDriverScreen()
                            }
                            "View driver" -> {
                                ViewAllDriversScreen(viewAllDriversModel = viewModel.viewAllDrivers)
                            }
                            "View work reports" -> {
                                ViewWorkReportContent(viewAllDoneWork = viewModel.myComplaints)
                            }
                            "User details" -> {
                                UserDetailsContent(viewAllUsersDetails = viewModel.viewAllUsers)
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
                    com.example.garbagemanagementsystemapp.AppBar(
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
                                id = "Create bin",
                                title = stringResource(R.string.Create_bin),
                                contentDescription = "Go to settings screen",
                                icon = Icons.Default.Create
                            ),
                            MenuItem(
                                id = "Update bin's status",
                                title = stringResource(R.string.Update_bin),
                                contentDescription = "Go to settings screen",
                                icon = Icons.Default.Update
                            ),
                            MenuItem(
                                id = "Create driver",
                                title = stringResource(R.string.Create_driver),
                                contentDescription = "Create driver",
                                icon = Icons.Default.Create
                            ),
                            MenuItem(
                                id = "View driver",
                                title = stringResource(R.string.View_driver),
                                contentDescription = "View driver",
                                icon = Icons.Default.RemoveRedEye
                            ),
                            MenuItem(
                                id = "View work reports",
                                title = stringResource(R.string.View_Work_Reports),
                                contentDescription = "View work reports",
                                icon = Icons.Default.Report
                            ),
                            MenuItem(
                                id = "User details",
                                title = stringResource(R.string.User_details),
                                contentDescription = "User details",
                                icon = Icons.Default.Person
                            ),
                            MenuItem(
                                id = "My profile",
                                title = stringResource(R.string.My_profile),
                                contentDescription = "View driver",
                                icon = Icons.Default.Face
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
                                "Home" -> {
                                    scope.launch {
                                        scaffoldState.drawerState.close()
                                    }
                                }
                                "Create bin" -> {
                                    selectedItem.value = MenuItem(
                                        id = "Create bin",
                                        title = "Create bin",
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
                                        icon = Icons.Default.Update
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
                                "Create driver" -> {
                                    selectedItem.value = MenuItem(
                                        id = "Create driver",
                                        title = "Create driver",
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
                                "View driver" -> {
                                    selectedItem.value = MenuItem(
                                        id = "View driver",
                                        title = "View driver",
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
                                "View work reports" -> {
                                    selectedItem.value = MenuItem(
                                        id = "View work reports",
                                        title = "View work reports",
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
                                "User details" -> {
                                    selectedItem.value = MenuItem(
                                        id = "User details",
                                        title = "User details",
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
                                "My profile" -> {
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
                                "Language" -> {
                                    val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    ContextCompat.startActivity(
                                        ApplicationFirebaseAuth.context,
                                        intent,
                                        null
                                    )
                                }
                                "Logout" -> viewModel.signOut()
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
                                    stringResource(R.string.Create_bin),
                                    painterResource(id = R.drawable.create_bin)
                                ),
                                UserViewPair(
                                    stringResource(R.string.Update_bin),
                                    painterResource(id = R.drawable.update_bin_status)
                                ),
                                UserViewPair(
                                    stringResource(R.string.Create_driver),
                                    painterResource(id = R.drawable.create_driver)
                                ),
                                UserViewPair(
                                    stringResource(R.string.View_driver),
                                    painterResource(id = R.drawable.view_driver)
                                ),
                                UserViewPair(
                                    stringResource(R.string.View_Work_Reports),
                                    painterResource(id = R.drawable.view_work_reports)
                                ),
                                UserViewPair(
                                    stringResource(R.string.User_details),
                                    painterResource(id = R.drawable.user_details)
                                ),
                                UserViewPair(
                                    stringResource(R.string.My_profile),
                                    painterResource(id = R.drawable.my_profile)
                                )
                            ), onItemClick = {
                                when (it.name) {
                                    "Create bin" -> {
                                        selectedItem.value = MenuItem(
                                            id = "Create bin",
                                            title = "Create bin",
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
                                    "Update bin's status" -> {
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
                                    "Create driver" -> {
                                        selectedItem.value = MenuItem(
                                            id = "Create driver",
                                            title = "Create driver",
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
                                    "View driver" -> {
                                        selectedItem.value = MenuItem(
                                            id = "View driver",
                                            title = "View driver",
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
                                    "View work reports" -> {
                                        selectedItem.value = MenuItem(
                                            id = "View work reports",
                                            title = "View work reports",
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
                                    "User details" -> {
                                        selectedItem.value = MenuItem(
                                            id = "User details",
                                            title = "User details",
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
                                    "My profile" -> {
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



