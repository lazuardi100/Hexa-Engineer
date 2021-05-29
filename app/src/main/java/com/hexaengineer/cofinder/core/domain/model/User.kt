package com.hexaengineer.cofinder.core.domain.model

data class User(
    val id: Int,
    val name: String,
    val skills: String,
    val picture: String,
    val city: String
)