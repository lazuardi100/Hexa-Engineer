package com.hexaengineer.cofinder.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_detail")
data class UserDetailEntity(
    @PrimaryKey
    @NonNull
    val id: Int,
    val personalities: Any,
    val description: Any,
    val address: String,
    val kontak: String
)