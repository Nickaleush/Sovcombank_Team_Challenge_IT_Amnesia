package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.createCode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import javax.inject.Inject

class CreateCodeFragment: BaseFragment<CreateCodePresenterImpl>(), CreateCodeView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.create_code_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
    }

    override fun onBackPressed() {
        requireActivity().finish()
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createCreateCodeFragment()
            .inject(this)
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}