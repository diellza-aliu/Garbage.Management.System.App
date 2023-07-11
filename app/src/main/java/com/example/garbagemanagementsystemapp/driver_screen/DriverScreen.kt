package com.example.garbagemanagementsystemapp

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
import com.example.garbagemanagementsystemapp.data_classes.MenuItem
import com.example.garbagemanagementsystemapp.data_classes.UserViewPair
import com.example.garbagemanagementsystemapp.driver_screen.DriverScreenViewModel
import com.example.garbagemanagementsystemapp.driver_screen.collect_trash_bins.CollectTrashBinScreen
import com.example.garbagemanagementsystemapp.navigation.NavigationGraph
import com.example.garbagemanagementsystemapp.ui.theme.composables.DrawerBody
import com.example.garbagemanagementsystemapp.ui.theme.composables.DrawerHeader
import com.example.garbagemanagementsystemapp.ui.theme.composables.userServicesRecyclerView
import com.example.garbagemanagementsystemapp.user_screen.my_profile.MyProfileScreen
import com.example.garbagemanagementsystemapp.user_screen.update_bin.UpdateBinScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
@ExperimentalComposeUiApi
fun DriverScreen(viewModel: DriverScreenViewModel = hiltViewModel()) {

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
                            "Ready to collect trash bins" -> {
                                CollectTrashBinScreen(myComplaints = viewModel.myComplaints)
                            }
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
                                id = "home",
                                title = stringResource(R.string.Home),
                                contentDescription = "Go to home screen",
                                icon = Icons.Default.Home
                            ),
                            MenuItem(
                                id = "Ready to collect trash bins",
                                title = stringResource(R.string.Collect_trash),
                                contentDescription = "Go to settings screen",
                                icon = Icons.Default.Delete
                            ),
                            MenuItem(
                                id = "Update bin's status",
                                title = stringResource(R.string.Update_bin),
                                contentDescription = "Go to settings screen",
                                icon = Icons.Default.Update
                            ),
                            MenuItem(
                                id = "My Profile",
                                title = stringResource(R.string.My_profile),
                                contentDescription = "Go to settings screen",
                                icon = Icons.Default.AccountCircle
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
                                "Ready to collect trash bins" -> {
                                    selectedItem.value = MenuItem(
                                        id = "Ready to collect trash bins",
                                        title = "Ready to collect trash bins",
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
                                    stringResource(id = R.string.Collect_trash),
                                    painterResource(id = R.drawable.collect_bin)
                                ),
                                UserViewPair(
                                    stringResource(id = R.string.Update_bin),
                                    painterResource(id = R.drawable.update_bin_status)
                                ),
                                UserViewPair(
                                    stringResource(id = R.string.My_profile),
                                    painterResource(id = R.drawable.my_profile)
                                )
                            ), onItemClick = {
                                when (it.name) {
                                    "Ready to collect trash bins" -> {
                                        selectedItem.value = MenuItem(
                                            id = "Ready to collect trash bins",
                                            title = "Ready to collect trash bins",
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



