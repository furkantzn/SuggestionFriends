package com.example.suggestfriend.ui.screens.suggestionfriend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suggestfriend.data.model.User
import com.example.suggestfriend.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuggestionFriendViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())

    private val _threeUsers = MutableStateFlow<List<User>>(emptyList())
    val threeUsers: StateFlow<List<User>> = _threeUsers.asStateFlow()
    private var currentIndex = 0

    fun initUsers() {
        currentIndex = 0
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _users.value = userRepository.fetchUsers().firstOrNull()!!
                getThreeUsers()
            }
        }
    }

    fun getThreeUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.Main.immediate){
                val nextUsers = _users.value.drop(currentIndex).take(3)
                if (nextUsers.isNotEmpty()) {
                    _threeUsers.value = nextUsers
                    currentIndex += nextUsers.size
                } else {
                    _threeUsers.value = emptyList()
                }
            }
        }
    }
}