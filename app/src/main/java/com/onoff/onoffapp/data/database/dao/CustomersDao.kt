package com.onoff.onoffapp.data.database.dao

import androidx.room.*
import com.onoff.onoffapp.data.database.entities.CustomersEntity
/**
 * Created by hans fritz ortega on 20/06/02.
 */
@Dao
interface CustomersDao {

    @Query("SELECT * FROM customers")
    suspend fun getCustomers(): List<CustomersEntity>

    @Query("SELECT * FROM customers WHERE cName1 LIKE :query OR cName2 LIKE :query OR cLastName1 LIKE :query OR cLastName2 LIKE :query  OR cDocument LIKE :query ")
    suspend fun getQueryCustomers(query: String): List<CustomersEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: List<CustomersEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomers(userEntity: CustomersEntity)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(userEntity: CustomersEntity)

    @Query("DELETE FROM customers ")
    suspend fun delete()

    @Query("DELETE FROM customers WHERE cId=:cId")
    suspend fun deleteQuery(cId: Int)
}
