package com.hexaengineer.cofinder.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hexaengineer.cofinder.core.data.source.remote.response.UserItem

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<UserItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(tourism: List<UserItem>)
}
