package com.rbs.valorantwiki.config

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailAgent : Screen("home/{agentId}") {
        fun createRoute(agentId: Long) = "home/$agentId"
    }
}