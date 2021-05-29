package com.hexaengineer.cofinder.core.data.source.remote.network

import com.hexaengineer.cofinder.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("get/user")
    fun getUserList(): Call<UserResponse>
}