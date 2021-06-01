package com.hexaengineer.cofinder.core.domain.repository

import androidx.lifecycle.LiveData
import com.hexaengineer.cofinder.core.data.Resource
import com.hexaengineer.cofinder.core.domain.model.DetailUser
import com.hexaengineer.cofinder.core.domain.model.User

interface IUserRepository {

    fun getAllUser(): LiveData<Resource<List<User>>>

    fun getUserDetail(id: String): LiveData<Resource<List<DetailUser>>>

}
