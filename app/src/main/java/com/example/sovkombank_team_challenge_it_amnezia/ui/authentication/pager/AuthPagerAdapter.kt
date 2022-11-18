package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.auth.AuthFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.logo.LogoFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment

class AuthPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) WelcomeFragment() else AuthFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}