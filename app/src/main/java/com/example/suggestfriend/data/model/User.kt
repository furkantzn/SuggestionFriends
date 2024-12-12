package com.example.suggestfriend.data.model

data class User (
    val id: Int,
    val username: String,
    val name: String,
    val age: Int,
    val interests: List<String>,
    val mutualFriends: Int,
    val profilePicture: String
)