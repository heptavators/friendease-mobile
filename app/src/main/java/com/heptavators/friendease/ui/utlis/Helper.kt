package com.heptavators.friendease.ui.utlis

import com.heptavators.friendease.ui.navigation.Screen
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.truncateText(maxLength: Int = 80): String {
    return if (this.length <= maxLength) {
        this
    } else {
        this.substring(0, maxLength - 3) + "..."
    }
}

fun formatDateString(inputDateString: String, outputFormat: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern(outputFormat)

    val date = LocalDate.parse(inputDateString, inputFormatter)
    return date.format(outputFormatter)
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
