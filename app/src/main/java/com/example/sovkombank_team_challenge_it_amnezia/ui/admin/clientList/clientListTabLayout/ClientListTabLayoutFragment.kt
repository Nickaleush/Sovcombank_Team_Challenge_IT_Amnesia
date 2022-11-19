package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListTabLayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment.Companion.AUTH_AS_ADMIN
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
        MaterialDialog.Builder(requireContext())
            .content(getString(R.string.ExitConfirm))
            .positiveText(R.string.Yes)
            .negativeText(R.string.No)
            .contentColor(resources.getColor(R.color.black, null))
            .positiveColor(resources.getColor(R.color.blue, null))
            .negativeColor(resources.getColor(R.color.red, null))
            .onPositive { materialDialog, _ ->
                materialDialog.dismiss()
                AUTH_AS_ADMIN = false
                requireActivity().finish()
            }
            .onNegative { materialDialog, _ ->
                materialDialog.dismiss()
                AUTH_AS_ADMIN = false
            }.show()
    }

}