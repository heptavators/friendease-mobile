package com.heptavators.friendease.data.di

import android.content.Context
import com.heptavators.friendease.data.api.ApiConfig
import com.heptavators.friendease.data.pref.UserPreference
import com.heptavators.friendease.data.pref.userDataStore
import com.heptavators.friendease.data.repository.NotificationRepository
import com.heptavators.friendease.data.repository.TalentRepository
import com.heptavators.friendease.data.repository.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val userPref = UserPreference.getInstance(context.userDataStore)
        return UserRepository.getInstance(apiService, userPref)
    }

    fun provideTalentRepository(context: Context): TalentRepository {
        val apiService = ApiConfig.getApiService()
        val userPref = UserPreference.getInstance(context.userDataStore)
        return TalentRepository.getInstance(apiService, userPref)
    }

    fun provideNotificationRepository(context: Context): NotificationRepository {
        val apiService = ApiConfig.getApiService()
        val userPref = UserPreference.getInstance(context.userDataStore)
        return NotificationRepository.getInstance(apiService, userPref)
    }
}