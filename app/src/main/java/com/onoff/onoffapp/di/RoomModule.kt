package com.onoff.onoffapp.di

import android.content.Context
import androidx.room.Room
import com.onoff.onoffapp.data.database.OnOffDataBase
import com.onoff.onoffapp.util.FlagConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, OnOffDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideUserDao(db: OnOffDataBase) = db.userDao()

    @Singleton
    @Provides
    fun provideCustomersDao(db: OnOffDataBase) = db.customersDao()

    @Singleton
    @Provides
    fun provideCustomersPhonesDao(db: OnOffDataBase) = db.customersPhonesDao()

    @Singleton
    @Provides
    fun providelogDao(db: OnOffDataBase) = db.logDao()
}