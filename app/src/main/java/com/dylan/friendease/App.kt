package com.dylan.friendease

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dylan.friendease.ui.navigation.NavigationItem
import com.dylan.friendease.ui.navigation.Screen
import com.dylan.friendease.ui.screen.home.HomeScreen
import com.dylan.friendease.ui.screen.welcome.WelcomeScreen
import com.dylan.friendease.ui.theme.roboto
import com.dylan.friendease.ui.utlis.showBottomBar
import kotlinx.coroutines.delay

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold (
        bottomBar = {
            if (currentRoute.showBottomBar()) {
                BottomBar(
                    navigator = { route: String ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    currentRoute = currentRoute ?: Screen.Home.route
                )
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.SplashScreen.route,
            modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.SplashScreen.route) {
                    SplashScreen(navController = navController)
                }
                composable(Screen.Welcome.route) {
                    WelcomeScreen(
                        navigateToLogin = {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                    )
                }
                composable(Screen.Login.route) {

                }
//                composable(Screen.Home.route) {
//                   HomeScreen(
//                        navigateToLogin = {
//                            navController.navigate(Screen.Welcome.route) {
//                                popUpTo(navController.graph.id) {
//                                    inclusive = true
//                                }
//                            }
//                        },
//                    )
//                }
                composable(
                    route = Screen.DetailTalent.route,
                    arguments = listOf(navArgument("idTalent") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
//                    DetailScreen(
//                        idTalent = backStackEntry.arguments?.getString("idTalent") ?: "",
//                        onNavigateToDetailScreen = { idTalent ->
//                            navController.navigate(Screen.DetailTalent.createRoute(idTalent))
//                        }
//                    )
                }
                composable(Screen.Talent.route) {
//                    FavoriteScreen()
                }
                composable(Screen.Search.route) {
    //                    FavoriteScreen()
                }

                composable(Screen.Schedule.route) {
    //                    FavoriteScreen()
                }
                composable(Screen.Notification.route) {
                    //                    FavoriteScreen()
                }

                composable(Screen.Profile.route) {
    //                    AboutScreen()
                }
            }
        }
}


@Composable
fun BottomBar(
    navigator: (String) -> Unit,
    currentRoute: String,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .background(MaterialTheme.colorScheme.tertiary)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)),
    ) {
        val iconSize = 24.dp

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.home_nav),
                icon = painterResource(id = R.drawable.ic_house_chimney),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.search_nav),
                icon = painterResource(id = R.drawable.ic_search),
                screen = Screen.Search
            ),
            NavigationItem(
                title = stringResource(R.string.schedule_nav),
                icon = painterResource(id = R.drawable.ic_calendar),
                screen = Screen.Schedule
            ),
            NavigationItem(
                title = stringResource(R.string.notification_nav),
                icon = painterResource(id = R.drawable.ic_bell),
                screen = Screen.Notification
            ),
            NavigationItem(
                title = stringResource(R.string.profile_nav),
                icon = painterResource(id = R.drawable.ic_user),
                screen = Screen.Profile
            ),
            )
        navigationItems.map { item ->
            val isSelected = currentRoute == item.screen.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.title,
                        tint = if (isSelected) {
                            MaterialTheme.colorScheme.tertiary
                        } else {
                            MaterialTheme.colorScheme.secondary
                        },
                        modifier = Modifier.size(iconSize)

                    )
                },
                label = {
                    Text(
                        item.title,
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.tertiary
                        } else {
                            MaterialTheme.colorScheme.secondary
                        },
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
//                    navController.navigate(item.screen.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        restoreState = true
//                        launchSingleTop = true
//                    }
                    navigator(item.screen.route)
                },
            )
        }
    }
}


@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        delay(1000L)
        navController.navigate(Screen.Home.route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FriendEase",
            fontSize = 50.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            modifier = Modifier,
            color = MaterialTheme.colorScheme.tertiary,
//            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
        )
    }
}