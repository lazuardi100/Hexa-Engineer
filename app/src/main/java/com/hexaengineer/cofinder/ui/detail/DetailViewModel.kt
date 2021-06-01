package com.hexaengineer.cofinder.ui.detail

import androidx.lifecycle.ViewModel
import com.hexaengineer.cofinder.core.domain.usecase.UserUseCase

class DetailViewModel(userUseCase: UserUseCase) : ViewModel() {
    private lateinit var id: String

    fun setId(userId: String){
        this.id = userId
    }

    val userDetail = userUseCase.getUserDetail(id)
}