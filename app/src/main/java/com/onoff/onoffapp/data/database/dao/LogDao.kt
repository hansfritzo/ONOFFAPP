package com.onoff.onoffapp.data.database.dao

import androidx.room.*
import com.onoff.onoffapp.data.database.entities.LogEntity

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@Dao
interface LogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: LogEntity)

    @Query("DELETE FROM log ")
    suspend fun delete()
}
