package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.auth.AuthPresenterImpl
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.auth.AuthView
import kotlinx.android.synthetic.main.auth_flow.*

class AuthPagerFragment : BaseFragment<AuthPresenterImpl>(), AuthView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.auth_flow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = AuthPagerAdapter(requireActivity())
        viewPager_auth_flow.adapter = pagerAdapter
        viewPager_auth_flow.setPageTransformer(ViewpagerSwipeAnimation())
        textViewNext.setOnClickListener { viewPager_auth_flow.currentItem = viewPager_auth_flow.currentItem + 1 }
    }

    override fun onBackPressed() {
        if (viewPager_auth_flow.currentItem == 0) {
            requireActivity().finish()
        } else {
            viewPager_auth_flow.currentItem = viewPager_auth_flow.currentItem - 1
        }
    }

    override fun createComponent() {
       App.instance
           .getAppComponent()
           .createAuthPagerFragment()
           .inject(this)
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}