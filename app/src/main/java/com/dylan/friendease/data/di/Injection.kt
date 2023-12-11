package com.dylan.friendease.data.di

import android.content.Context
import com.dylan.friendease.data.api.ApiConfig
import com.dylan.friendease.data.pref.UserPreference
import com.dylan.friendease.data.pref.userDataStore
import com.dylan.friendease.data.repository.TalentRepository
import com.dylan.friendease.data.repository.UserRepository

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
}