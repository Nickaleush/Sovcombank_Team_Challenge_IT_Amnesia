package com.example.sovkombank_team_challenge_it_amnezia.ui.activity

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseActivity
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment.Companion.AUTH_AS_ADMIN
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenterImpl>(), MainView {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.view = this
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val mainGraph = navController.navInflater.inflate(R.navigation.navigation_graph)
        navController.graph = mainGraph
        bottomNavigationView = findViewById(R.id.clientBottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.welcomeFragment -> hideBotNav()
                R.id.logoFragment -> hideBotNav()
                R.id.clientListTabLayoutFragment -> {
                    setUpAdminBottomNav()
                    bottomNavigationView.setupWithNavController(navController)
                }
                R.id.authFragment -> hideBotNav()
                R.id.profileFragment -> {
                    setUpClientBottomNav()
                    bottomNavigationView.setupWithNavController(navController)
                }
                R.id.loginFragment -> hideBotNav()
                R.id.createCodeFragment -> hideBotNav()
                R.id.registrationFragment -> hideBotNav()
                else -> showBotNav()
            }
        }
    }

    private fun setUpAdminBottomNav() {
        bottomNavigationView =  findViewById(R.id.adminBottomNavigationView)
        adminBottomNavigationView.visibility = View.VISIBLE
        clientBottomNavigationView.visibility = View.GONE
    }

    private fun setUpClientBottomNav() {
        bottomNavigationView = findViewById(R.id.clientBottomNavigationView)
        adminBottomNavigationView.visibility = View.GONE
        clientBottomNavigationView.visibility = View.VISIBLE
    }


    override fun onBackPressed() {
        if (BaseFragment.backPressedListener != null) {
            BaseFragment.backPressedListener!!.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    private fun hideBotNav() {
        bottomNavigationView.animate().alpha(0.0f).duration = 300
        bottomNavigationView.visibility = View.GONE
    }

    private fun showBotNav() {
        bottomNavigationView.animate()
            .alpha(1.0f).duration = 1000
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        bottomNavigationView.visibility = View.VISIBLE
    }

    interface OnBackPressedListener {
        fun onBackPressed()
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createMainActivity()
            .inject(this)
    }
}