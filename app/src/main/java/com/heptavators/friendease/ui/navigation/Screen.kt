package com.heptavators.friendease.ui.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Register : Screen("register")
    object Register2 : Screen("register2")

    object Home : Screen("home")
    object Search : Screen("search")
    object Schedule : Screen("schedule")
    object ScheduleDetail : Screen("schedule/{scheduleDetail}") {
        fun createRoute(scheduleDetail: String) = "schedule/$scheduleDetail"
    }
    object Payment : Screen("payment")
    object MidtransPayment : Screen("midtrans/payment/{paymentUrl}") {
        fun createRoute(paymentUrl: String) = "midtrans/payment/$paymentUrl"
    }
    object Notification : Screen("notification")
    object Profile : Screen("profile")
    object Talent : Screen("talent")
    object DetailTalent : Screen("detail/{idTalent}") {
        fun createRoute(idTalent: String) = "detail/$idTalent"
    }
}