package com.hexaengineer.cofinder.core.data.source.remote.network

import com.hexaengineer.cofinder.core.data.source.remote.response.UserDetailResponse
import com.hexaengineer.cofinder.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("get/user")
    fun getUserList(): Call<UserResponse>

    @GET("get/user/{id}")
    fun getUserDetail(
        @Path("id") id: String
    ): Call<UserDetailResponse>
}