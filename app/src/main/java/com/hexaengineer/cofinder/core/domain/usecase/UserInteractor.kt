package com.hexaengineer.cofinder.core.domain.usecase

import com.hexaengineer.cofinder.core.domain.repository.IUserRepository

class UserInteractor(private val userRepository: IUserRepository): UserUseCase {

    override fun getAllUser() = userRepository.getAllUser()

}