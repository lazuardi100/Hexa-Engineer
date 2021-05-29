package com.hexaengineer.cofinder.core.utils

import com.hexaengineer.cofinder.core.data.source.local.entity.UserEntity
import com.hexaengineer.cofinder.core.data.source.remote.response.UserItem
import com.hexaengineer.cofinder.core.domain.model.User

object DataMapper {
    fun mapResponsesToEntities(input: List<UserItem>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                id = it.id,
                name = it.name,
                skills = it.skills,
                picture = it.picture,
                city = it.city
            )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<UserItem>): List<User> =
        input.map {
            User(
                id = it.id,
                name = it.name,
                skills = it.skills,
                picture = it.picture,
                city = it.city
            )
        }

    fun mapDomainToEntity(input: User) = UserEntity(
        id = input.id,
        name = input.name,
        skills = input.skills,
        picture = input.picture,
        city = input.city
    )
}