package com.rbs.valorantwiki.model

data class Agent(
    val id: Long,
    val name: String,
    val description: String,
    val role: String,
    val photoUrl: String
)