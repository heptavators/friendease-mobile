package com.heptavators.friendease.ui.screen.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.heptavators.friendease.data.model.DataLocation
import com.heptavators.friendease.data.model.DataTags
import com.heptavators.friendease.data.model.LocationResponse
import com.heptavators.friendease.data.model.TagsResponse
import com.heptavators.friendease.data.model.UserData
import com.heptavators.friendease.data.repository.OrderRepository
import com.heptavators.friendease.data.repository.UserRepository
import com.heptavators.friendease.ui.components.UiState

class RegisterViewModel(
    private val repository: UserRepository,
): ViewModel() {
    private val _registerStatus = mutableStateOf<UiState<String>>(UiState.Loading)
    val registerStatus: State<UiState<String>> get() = _registerStatus

    private val _tags = mutableStateOf<UiState<TagsResponse>>(UiState.Loading)
    val tagsData: State<UiState<TagsResponse>> get() = _tags

    private val _location = mutableStateOf<UiState<LocationResponse>>(UiState.Loading)
    val locationData: State<UiState<LocationResponse>> get() = _location



    fun register(email: String, password: String) {
        repository.register(email, password).observeForever {
            when (it) {
                is UiState.Loading -> {
                    _registerStatus.value = UiState.Loading
                }
                is UiState.Success -> {
                    _registerStatus.value = UiState.Success(it.data)
                }
                is UiState.Error -> {
                    _registerStatus.value = UiState.Error(it.errorMessage)
                }
                is UiState.NotLogged -> {
                    _registerStatus.value = UiState.NotLogged
                }
            }
        }
    }

    fun getTags() {
        repository.getTags().observeForever {
            when (it) {
                is UiState.Loading -> {
                    _tags.value = UiState.Loading
                }
                is UiState.Success -> {
                    _tags.value = UiState.Success(it.data)
                }
                is UiState.Error -> {
                    _tags.value = UiState.Error(it.errorMessage)
                }
                is UiState.NotLogged -> {
                    _tags.value = UiState.NotLogged
                }
            }
        }
    }

    fun getLocation() {
        repository.getLocation().observeForever {
            when (it) {
                is UiState.Loading -> {
                    _location.value = UiState.Loading
                }
                is UiState.Success -> {
                    _location.value = UiState.Success(it.data)
                }
                is UiState.Error -> {
                    _location.value = UiState.Error(it.errorMessage)
                }
                is UiState.NotLogged -> {
                    _location.value = UiState.NotLogged
                }
            }
        }
    }

    fun changeProfile(fullname: String, username: String, bod: String, locationId: String, gender: String, userPreferences: String, tags: List<String>){
        repository.changeProfile(fullname, username, bod, locationId, gender, userPreferences, tags).observeForever {
            when (it) {
                is UiState.Loading -> {
                    _registerStatus.value = UiState.Loading
                }
                is UiState.Success -> {
                    _registerStatus.value = UiState.Success(it.data)
                }
                is UiState.Error -> {
                    _registerStatus.value = UiState.Error(it.errorMessage)
                }
                is UiState.NotLogged -> {
                    _registerStatus.value = UiState.NotLogged
                }
            }
        }
    }
}