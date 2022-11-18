package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.BuildConfig
import com.example.sovkombank_team_challenge_it_amnezia.domain.ServiceGenerator
import com.example.sovkombank_team_challenge_it_amnezia.domain.authorizationApi.AuthorizationApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.interceptors.AccessTokenInterceptor
import com.example.sovkombank_team_challenge_it_amnezia.domain.interceptors.NoneAuthInterceptor
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideNoneAuthApi(gson: Gson, noneAuthInterceptor: NoneAuthInterceptor, loggingInterceptor: HttpLoggingInterceptor): AuthorizationApi {
        val interceptors = arrayOf(noneAuthInterceptor, loggingInterceptor)
        return ServiceGenerator.generate(BuildConfig.BASE_URL, AuthorizationApi::class.java, gson, null, interceptors)
    }

    @Singleton
    @Provides
    fun provideAuthApi(gson: Gson, authInterceptor: AccessTokenInterceptor, loggingInterceptor: HttpLoggingInterceptor): MainApi {
        val interceptors = arrayOf(authInterceptor, loggingInterceptor)
        return ServiceGenerator.generate(BuildConfig.BASE_URL, MainApi::class.java, gson, null,  interceptors)
    }

}