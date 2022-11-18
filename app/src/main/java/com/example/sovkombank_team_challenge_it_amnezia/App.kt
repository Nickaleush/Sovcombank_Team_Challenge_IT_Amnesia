package com.example.sovkombank_team_challenge_it_amnezia

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.sovkombank_team_challenge_it_amnezia.di.AppComponent
import com.example.sovkombank_team_challenge_it_amnezia.di.AppModule
import com.example.sovkombank_team_challenge_it_amnezia.di.DaggerAppComponent

class App : MultiDexApplication() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setUpDagger()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun getAppComponent(): AppComponent = appComponent

    fun getContext(): Context? {
        return instance.applicationContext
    }

    private fun setUpDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext, this))
            .build()
        appComponent.inject(this)
    }

    companion object {
        lateinit var instance: App private set
    }
}