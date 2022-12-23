package com.rbs.valorantwiki

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rbs.valorantwiki.config.Screen
import com.rbs.valorantwiki.ui.detail.DetailScreen
import com.rbs.valorantwiki.ui.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ValorantApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val topBarState = rememberSaveable { (mutableStateOf(true)) }

    when (currentRoute) {
        Screen.Home.route -> {
            topBarState.value = true
        }
        Screen.About.route -> {
            topBarState.value = false
        }
        Screen.DetailAgent.route -> {
            topBarState.value = false
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                topBarState = topBarState
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { agentId ->
                        navController.navigate(Screen.DetailAgent.createRoute(agentId))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailAgent.route,
                arguments = listOf(navArgument("agentId") { type = NavType.LongType })
            ) {
                val id = it.arguments?.getLong("agentId") ?: -1L
                DetailScreen(agentId = id)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun TopBar(navController: NavController, topBarState: MutableState<Boolean>) {
    AnimatedVisibility(
        visible = topBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            TopAppBar(
                title = {
                    Text(text = "Valorant Wiki")
                },
                actions = {
                    if (topBarState.value) {
                        IconButton(onClick = {
                            navController.navigate(Screen.About.route)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "about_page",
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        }
    )
}