package com.heptavators.friendease.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.heptavators.friendease.AppViewModel
import com.heptavators.friendease.data.di.Injection
import com.heptavators.friendease.data.repository.NotificationRepository
import com.heptavators.friendease.data.repository.TalentRepository
import com.heptavators.friendease.data.repository.UserRepository
import com.heptavators.friendease.ui.screen.Search.SearchViewModel
import com.heptavators.friendease.ui.screen.detailTalent.DetailViewModel
import com.heptavators.friendease.ui.screen.home.HomeViewModel
import com.heptavators.friendease.ui.screen.login.LoginViewModel
import com.heptavators.friendease.ui.screen.notification.NotificationViewModel
import com.heptavators.friendease.ui.screen.profile.ProfileViewModel
import com.heptavators.friendease.ui.screen.welcome.WelcomeViewModel

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val talentRepository: TalentRepository,
    private val notificationRepository: NotificationRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AppViewModel::class.java) -> AppViewModel(userRepository) as T
            modelClass.isAssignableFrom(WelcomeViewModel::class.java) -> WelcomeViewModel(userRepository) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(userRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(talentRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(talentRepository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(talentRepository) as T
            modelClass.isAssignableFrom(NotificationViewModel::class.java) -> NotificationViewModel(notificationRepository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(userRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}


fun getViewModelFactory(context: Context): ViewModelFactory {
    return ViewModelFactory(
        Injection.provideUserRepository(context),
        Injection.provideTalentRepository(context),
        Injection.provideNotificationRepository(context),
        )
}