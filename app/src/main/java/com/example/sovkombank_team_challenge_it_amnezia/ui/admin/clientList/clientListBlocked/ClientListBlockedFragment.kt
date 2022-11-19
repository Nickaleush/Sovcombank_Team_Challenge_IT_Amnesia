package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import kotlinx.android.synthetic.main.client_list_blocked_fragment.*

class ClientListBlockedFragment: BaseFragment<ClientListBlockedPresenterImpl>(),
    ClientListBlockedView {


    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createClientListBlockedFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.client_list_blocked_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this

        emptyBlockedClientListTextView.visibility = View.VISIBLE
        emptyBlockedClientListImageView.visibility = View.VISIBLE

    }
    override fun onBackPressed() {
        TODO("Not yet implemented")
    }
}