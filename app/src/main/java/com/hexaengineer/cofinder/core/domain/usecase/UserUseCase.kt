package com.hexaengineer.cofinder.core.domain.usecase

import androidx.lifecycle.LiveData
import com.hexaengineer.cofinder.core.data.Resource
import com.hexaengineer.cofinder.core.domain.model.User

interface UserUseCase {
    fun getAllUser(): LiveData<Resource<List<User>>>
}