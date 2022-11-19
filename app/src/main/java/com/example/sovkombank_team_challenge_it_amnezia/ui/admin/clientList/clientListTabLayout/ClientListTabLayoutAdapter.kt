package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListTabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListActive.ClientListActiveFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked.ClientListBlockedFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientNotConfirmedList.ClientListNotConfirmedFragment

class ClientListTabLayoutAdapter (
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ClientListActiveFragment()
            1 -> ClientListNotConfirmedFragment()
            else -> ClientListBlockedFragment()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}
