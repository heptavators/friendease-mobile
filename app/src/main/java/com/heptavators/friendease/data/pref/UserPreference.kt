package com.heptavators.friendease.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.heptavators.friendease.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
class UserPreference private constructor(private val userDataStore: DataStore<Preferences>) {
    suspend fun saveUser(user: User) {
        userDataStore.edit { preferences ->
            preferences[bearerToken] =user.token
        }
    }

    fun getUser(): Flow<User> = userDataStore.data.map { preferences ->
        User(
            token = preferences[bearerToken] ?: "",
        )
    }

//    fun getToken() = userDataStore.data.map { preferences ->
//        preferences[bearerToken]
//    }

    suspend fun deleteUser() {
        userDataStore.edit { preferences ->
            preferences.clear()
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null
        private val bearerToken = stringPreferencesKey("bearer_token")


        fun getInstance(userDataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(userDataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}