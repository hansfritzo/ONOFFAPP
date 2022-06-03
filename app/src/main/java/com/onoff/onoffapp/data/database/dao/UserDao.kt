package com.onoff.onoffapp.data.database.dao

import androidx.room.*
import com.onoff.onoffapp.data.database.entities.UserEntity

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getUser(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: List<UserEntity>)

    @Query("DELETE FROM user ")
    suspend fun delete()
}
