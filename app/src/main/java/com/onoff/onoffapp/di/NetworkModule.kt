package com.onoff.onoffapp.di

import OnOffErrorHandler
import com.onoff.onoffapp.data.network.apiClient.CustomersApiClient
import com.onoff.onoffapp.data.network.apiClient.CustomersPhonesApiClient
import com.onoff.onoffapp.data.network.apiClient.UserApiClient
import com.onoff.onoffapp.util.FlagConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(OnOffErrorHandler()).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserApiClient(retrofit: Retrofit): UserApiClient {
        return retrofit.create(UserApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideCustomersApiClient(retrofit: Retrofit): CustomersApiClient {
        return retrofit.create(CustomersApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideCustomersPhonesApiClient(retrofit: Retrofit): CustomersPhonesApiClient {
        return retrofit.create(CustomersPhonesApiClient::class.java)
    }

}
