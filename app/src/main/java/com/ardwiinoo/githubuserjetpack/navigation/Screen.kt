package com.ardwiinoo.githubuserjetpack.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")

    object Detail : Screen("home/{username}") {
        fun createRoute(username: String) = "home/$username"
    }
}