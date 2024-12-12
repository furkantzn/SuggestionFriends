package com.example.suggestfriend.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.suggestfriend.ui.screens.home.HomeScreen
import com.example.suggestfriend.ui.screens.suggestionfriend.SuggestionFriendScreen

@Composable
fun SetUpNavHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Destinations.Home
    ) {
        composable(route = Destinations.Home){
            HomeScreen(navController = navController)
        }
        composable(
            route = Destinations.SuggestionFriend,
            arguments = listOf(navArgument("countDownTime") { type = NavType.FloatType })
        ) { backStackEntry ->
            val countDownTime = backStackEntry.arguments?.getFloat("countDownTime")
            if (countDownTime != null) {
                SuggestionFriendScreen(countDownTime = countDownTime)
            }
        }
    }
}