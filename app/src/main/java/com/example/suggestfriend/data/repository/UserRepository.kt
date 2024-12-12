package com.example.suggestfriend.data.repository

import com.example.suggestfriend.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository {
    fun fetchUsers(): Flow<List<User>> {
        return flow {
            emit( // Burada veriyi yaymanız gerekiyor
                listOf(
                    User(
                        1,
                        "johndoe",
                        "John Doe",
                        28,
                        listOf("Technology", "Music", "Movies"),
                        5,
                        "https://randomuser.me/api/portraits/men/1.jpg"
                    ),
                    User(
                        2,
                        "janedoe",
                        "Jane Doe",
                        25,
                        listOf("Art", "Travel", "Cooking"),
                        3,
                        "https://randomuser.me/api/portraits/women/1.jpg"
                    ),
                    User(
                        3,
                        "robert89",
                        "Robert Smith",
                        34,
                        listOf("Gaming", "Fitness", "Technology"),
                        8,
                        "https://randomuser.me/api/portraits/men/2.jpg"
                    ),
                    User(
                        4,
                        "emily_r",
                        "Emily Roberts",
                        30,
                        listOf("Reading", "Photography", "Yoga"),
                        4,
                        "https://randomuser.me/api/portraits/women/2.jpg"
                    ),
                    User(
                        5,
                        "johnDoe",
                        "John Doe",
                        25,
                        listOf("Reading", "Gaming", "Hiking"),
                        10,
                        "https://randomuser.me/api/portraits/men/1.jpg"
                    ),
                    User(
                        6,
                        "emmaStone",
                        "Emma Stone",
                        28,
                        listOf("Cooking", "Photography", "Running"),
                        8,
                        "https://randomuser.me/api/portraits/women/2.jpg"
                    ),
                    User(
                        7,
                        "jamesSmith",
                        "James Smith",
                        30,
                        listOf("Traveling", "Swimming", "Music"),
                        12,
                        "https://randomuser.me/api/portraits/men/3.jpg"
                    ),
                    User(
                        8,
                        "oliviaBrown",
                        "Olivia Brown",
                        22,
                        listOf("Dancing", "Painting", "Writing"),
                        7,
                        "https://randomuser.me/api/portraits/women/4.jpg"
                    ),
                    User(
                        9,
                        "liamJones",
                        "Liam Jones",
                        26,
                        listOf("Cycling", "Fishing", "Movies"),
                        9,
                        "https://randomuser.me/api/portraits/men/5.jpg"
                    ),
                    User(
                        10,
                        "sophiaTaylor",
                        "Sophia Taylor",
                        24,
                        listOf("Yoga", "Crafting", "Gardening"),
                        11,
                        "https://randomuser.me/api/portraits/women/6.jpg"
                    ),
                    User(
                        11,
                        "masonDavis",
                        "Mason Davis",
                        27,
                        listOf("Basketball", "Coding", "Reading"),
                        10,
                        "https://randomuser.me/api/portraits/men/7.jpg"
                    ),
                    User(
                        12,
                        "miaWilson",
                        "Mia Wilson",
                        23,
                        listOf("Fitness", "Photography", "Traveling"),
                        8,
                        "https://randomuser.me/api/portraits/women/8.jpg"
                    ),
                    User(
                        13,
                        "ethanMartinez",
                        "Ethan Martinez",
                        29,
                        listOf("Chess", "Skiing", "Music"),
                        10,
                        "https://randomuser.me/api/portraits/men/9.jpg"
                    ),
                    User(
                        14,
                        "avaWhite",
                        "Ava White",
                        21,
                        listOf("Dancing", "Movies", "Traveling"),
                        7,
                        "https://randomuser.me/api/portraits/women/10.jpg"
                    )
                )
            )
        }
    }
}