package com.example.sovkombank_team_challenge_it_amnezia.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val appContext: Context,
    private val application: Application
) {
    @Singleton
    @Provides
    fun provideApplication(): Application = application

    @Singleton
    @Provides
    fun provideContext(): Context = appContext
}