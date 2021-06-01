package com.hexaengineer.cofinder.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hexaengineer.cofinder.core.data.source.local.LocalDataSource
import com.hexaengineer.cofinder.core.data.source.remote.RemoteDataSource
import com.hexaengineer.cofinder.core.data.source.remote.network.ApiResponse
import com.hexaengineer.cofinder.core.data.source.remote.response.UserItem
import com.hexaengineer.cofinder.core.domain.model.User
import com.hexaengineer.cofinder.core.domain.repository.IUserRepository
import com.hexaengineer.cofinder.core.utils.AppExecutors
import com.hexaengineer.cofinder.core.utils.DataMapper

class UserRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllUser(): LiveData<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<User>> {
                return Transformations.map(localDataSource.getAllUser()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                data == null || data.isEmpty()
            //true

            override fun createCall(): LiveData<ApiResponse<List<UserItem>>> =
                remoteDataSource.getAllUser()

            override fun saveCallResult(data: List<UserItem>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asLiveData()
}

