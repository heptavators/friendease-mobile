package com.heptavators.friendease.ui.utlis

import android.os.Build
import com.heptavators.friendease.ui.navigation.Screen
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Base64

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

fun convertToAmPmFormat(time24: String): String {
    val formatter24 = DateTimeFormatter.ofPattern("HH:mm")
    val formatterAmPm = DateTimeFormatter.ofPattern("hh:mm a")

    val localTime = LocalTime.parse(time24, formatter24)
    return localTime.format(formatterAmPm)
}


private fun decodeToken(jwt: String): String {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return "Requires SDK 26"
    val parts = jwt.split(".")
    return try {
        val charset = charset("UTF-8")
        val header = String(Base64.getUrlDecoder().decode(parts[0].toByteArray(charset)), charset)
        val payload = String(Base64.getUrlDecoder().decode(parts[1].toByteArray(charset)), charset)
        "$header"
        "$payload"
    } catch (e: Exception) {
        "Error parsing JWT: $e"
    }
}