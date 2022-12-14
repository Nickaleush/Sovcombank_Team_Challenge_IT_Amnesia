package com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserDTO
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.utils.navigateTo
import kotlinx.android.synthetic.main.admin_profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.*
import javax.inject.Inject

class ProfileFragment: BaseFragment<ProfilePresenterImpl>(), ProfileView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createProfileFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        getData()
        buttonClientLogOut.setOnClickListener {
            sharedPreferences.accessToken = null
            sharedPreferences.pinCode = null
            findNavController().navigateTo(findNavController(),R.id.action_profileFragment_to_welcomeFragment, true)
        }
    }

    private fun getData() {
        presenter.getUserInfo()
    }

    override fun onBackPressed() {
      requireActivity().onBackPressedDispatcher.onBackPressed()
    }


    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    override fun initUserInfo(user: UserDTO) {
        if (this.isVisible) {
            clientCredentialsTextView.text = user.credentials
            (user.firstName + " " + user.middleName + " " + user.lastName).also {
                clientFullNameTextView.text = it
            }
            clientBirthDayTextView.text = user.birthDate
        }
    }
}