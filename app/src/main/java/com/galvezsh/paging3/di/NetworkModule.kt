package com.galvezsh.paging3.di

import com.galvezsh.paging3.data.RickAndMortyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn( SingletonComponent::class )
object NetworkModule {

    private const val BASE_URL = "https://rickandmortyapi.com/"

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun provideRetrofit( okHttpClient: OkHttpClient ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl( BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .client( okHttpClient )
            .build()

    @Provides
    fun provideApiService( retrofit: Retrofit ): RickAndMortyApiService = retrofit.create( RickAndMortyApiService::class.java )

}