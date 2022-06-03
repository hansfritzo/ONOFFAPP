package com.onoff.onoffapp.data.database.dao

import androidx.room.*
import com.onoff.onoffapp.data.database.entities.CustomersPhonesEntity

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@Dao
interface CustomersPhonesDao {

    @Query("SELECT * FROM customers_phones WHERE cId=:cId")
    suspend fun getCustomerPhones(cId:Int): List<CustomersPhonesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customersPhonesEntity: List<CustomersPhonesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(customersPhonesEntity: CustomersPhonesEntity)

    @Query("DELETE FROM customers_phones ")
    suspend fun delete()
}
