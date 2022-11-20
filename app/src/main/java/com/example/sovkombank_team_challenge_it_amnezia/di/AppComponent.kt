package com.example.sovkombank_team_challenge_it_amnezia.di

import android.content.Context
import android.content.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.di.modules.LocalDataModule
import com.example.sovkombank_team_challenge_it_amnezia.di.modules.NetworkModule
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.di.components.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        LocalDataModule::class,
        NetworkModule::class,
    ]
)

interface AppComponent {

    fun inject(application: App)

    fun context(): Context

    fun provideSharedPreferences(): SharedPreferences

    fun createMainActivity(): MainComponent

    fun createAuthFragment(): AuthComponent

    fun createAuthPagerFragment():AuthPagerComponent

    fun createProfileFragment(): ProfileComponent

    fun createLogoFragment(): LogoComponent

    fun createWelcomeFragment(): WelcomeComponent

    fun createRegistrationFragment():RegistrationComponent

    fun createLoginFragment(): LoginComponent

    fun createCreateCodeFragment(): CreateCodeComponent

    fun createClientListTabLayoutFragment(): ClientListTabLayoutComponent

    fun createClientListActiveFragment(): ClientListActiveComponent

    fun createClientListBlockedFragment(): ClientListBlockedComponent

    fun createAdminProfileFragment(): AdminProfileComponent

    fun createClientListNotConfirmedFragment(): ClientListNotConfirmedComponent

    fun createHomeFragment(): HomeComponent

    fun createFirebaseMessagingItAmnesiaService(): FirebaseMessagingItAmnesiaComponent

    fun createTransactionHistoryFragment(): TransactionHistoryComponent

    fun createStatisticsFragment(): StatisticsComponent

    fun createPredictionCurrencyFragment(): PredictionCurrencyComponent

}