package com.dylan.friendease.ui.utlis

import com.dylan.friendease.ui.navigation.Screen

fun String.truncateText(maxLength: Int = 80): String {
    return if (this.length <= maxLength) {
        this
    } else {
        this.substring(0, maxLength - 3) + "..."
    }
}

fun String?.showBottomBar(): Boolean {
    return when (this) {
        Screen.Home.route -> true
        Screen.Search.route -> true
        Screen.Schedule.route -> true
        Screen.Payment.route -> true
        Screen.Notification.route -> true
        Screen.Profile.route -> true
        else -> false
    }
}
