package com.hexaengineer.cofinder.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PostPersonalityResponse(
    @field:SerializedName("words")
    val personality: String
)