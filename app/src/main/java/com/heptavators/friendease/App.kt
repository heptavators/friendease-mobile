package com.heptavators.friendease

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.heptavators.friendease.ui.navigation.NavigationItem
import com.heptavators.friendease.ui.navigation.Screen
import com.heptavators.friendease.ui.screen.Search.SearchScreen
import com.heptavators.friendease.ui.screen.detailTalent.DetailTalentScreen
import com.heptavators.friendease.ui.screen.getViewModelFactory
import com.heptavators.friendease.ui.screen.home.HomeScreen
import com.heptavators.friendease.ui.screen.login.LoginScreen
import com.heptavators.friendease.ui.screen.notification.NotificationScreen
import com.heptavators.friendease.ui.screen.payment.MidtransPayment
import com.heptavators.friendease.ui.screen.payment.PaymentScreen
import com.heptavators.friendease.ui.screen.profile.ProfileScreen
import com.heptavators.friendease.ui.screen.register.RegisterScreen
import com.heptavators.friendease.ui.screen.schedule.ScheduleScreen
import com.heptavators.friendease.ui.screen.welcome.WelcomeScreen
import com.heptavators.friendease.ui.utlis.showBottomBar
import kotlinx.coroutines.delay

@Composable
fun App(
    modifier: Modifier = Modifier,
    makePayment: () -> Unit,
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = viewModel(
        factory = getViewModelFactory(context = LocalContext.current)
    ),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val validateToken by viewModel.isHaveToken

    LaunchedEffect(validateToken) {
        viewModel.validateToken()
    }

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
                        navigateToRegister = {
                            navController.navigate(Screen.Register.route) {
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
                composable(Screen.Home.route) {
                    HomeScreen(
                        navigateToLogin = {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToWelcome = {
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToDetail = { id ->
                            navController.navigate(Screen.DetailTalent.createRoute(id))
                        },
                    )
                }
                composable(Screen.Login.route) {
                    LoginScreen(
                        navigateToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                    )
                }
                composable(Screen.Register.route) {
                    RegisterScreen(
                        navigateToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                    )
                }
                composable(
                    route = Screen.DetailTalent.route,
                    arguments = listOf(navArgument("idTalent") { type = NavType.StringType })
                ) { backStackEntry ->
                    val idTalent = backStackEntry.arguments?.getString("idTalent") ?: ""
                    DetailTalentScreen(
                        id = idTalent,
                        navigateToBack = {
                            navController.popBackStack()
                        },
                        makePayment = {
                            navController.navigate(Screen.MidtransPayment.route)
                        },

                    )
                }
                composable(
                    route = Screen.MidtransPayment.route
                ){
                    MidtransPayment(
                        navigateToBack = {
                            navController.popBackStack()
                        },
                    )
                }
                composable(Screen.Talent.route) {
//                    FavoriteScreen()
                }
                composable(Screen.Search.route) {
                    SearchScreen(
                        navigateToLogin = {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToWelcome = {
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToDetail = { id ->
                            navController.navigate(Screen.DetailTalent.createRoute(id))
                        },
                    )
                }
                composable(Screen.Schedule.route) {
                    ScheduleScreen(
                        navigateToLogin = {
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                    )
                }
                composable(Screen.Notification.route) {
                    NotificationScreen(
                        navigateToLogin = {
                            navController.navigate(Screen.Notification.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                    )
                }
                composable(Screen.Payment.route) {
                    PaymentScreen()
                }
                composable(Screen.Profile.route) {
                   ProfileScreen(
                        navigateToLogin = {
                            navController.navigate(Screen.Welcome.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        },
                       navigateToNotification = {
                           navController.navigate(Screen.Notification.route) {
                               popUpTo(navController.graph.id) {
                                   inclusive = true
                               }
                           }
                       },
                       navigateToWelcome = {
                           navController.navigate(Screen.Welcome.route) {
                               popUpTo(navController.graph.id) {
                                   inclusive = true
                               }
                           }
                       },
                    )
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
        val iconSize = 22.dp

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
                title = stringResource(R.string.payment_nav),
                icon = painterResource(id = R.drawable.ic_credit_card),
                screen = Screen.Payment
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
        navController.navigate(Screen.Welcome.route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_friendease),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
        )
    }
}