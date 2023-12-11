package com.dylan.friendease.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dylan.friendease.data.di.Injection
import com.dylan.friendease.data.repository.TalentRepository
import com.dylan.friendease.data.repository.UserRepository
import com.dylan.friendease.ui.screen.home.HomeViewModel
import com.dylan.friendease.ui.screen.login.LoginViewModel
import com.dylan.friendease.ui.screen.profile.ProfileViewModel

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val talentRepository: TalentRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(userRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(talentRepository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(userRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}


fun getViewModelFactory(context: Context): ViewModelFactory {
    return ViewModelFactory(
        Injection.provideUserRepository(context),
        Injection.provideTalentRepository(context),
        )
}