package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListTabLayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.client_list_tablayout_fragment.*

class ClientListTabLayoutFragment: BaseFragment<ClientListTabLayoutPresenterImpl>(), ClientListTabLayoutView {


    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createClientListTabLayoutFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.client_list_tablayout_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this

        val pagerAdapter = ClientListTabLayoutAdapter(requireActivity())
        viewpager2ClientList.adapter = pagerAdapter
        TabLayoutMediator(
            tab_layout,
            viewpager2ClientList,
        ) { tab, position ->
            val tabNames = listOf(getString(R.string.Active), getString(R.string.Blocked))
            tab.text = tabNames[position]
        }.attach()
    }

    override fun onBackPressed() {
        TODO("Not yet implemented")
    }

}