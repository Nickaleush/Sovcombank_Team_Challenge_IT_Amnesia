package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserDTO
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.utils.navigateTo
import kotlinx.android.synthetic.main.admin_profile_fragment.*
import javax.inject.Inject

class AdminProfileFragment: BaseFragment<AdminProfilePresenterImpl>(),
    AdminProfileView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createAdminProfileFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.admin_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        presenter.getUserInfo()
        buttonAdminLogOut.setOnClickListener {
            sharedPreferences.accessToken = null
            sharedPreferences.pinCode = null
            findNavController().navigateTo(findNavController(),R.id.action_adminProfileFragment_to_welcomeFragment, true)
        }
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
                sharedPreferences.adminMode = false
                sharedPreferences.pinCode = null
                requireActivity().finish()
            }
            .onNegative { materialDialog, _ ->
                materialDialog.dismiss()
                sharedPreferences.adminMode = false
                sharedPreferences.pinCode = null
            }.show()
    }

    override fun initUserInfo(user: UserDTO) {
        adminCredentialsTextView.text = user.credentials
        (user.firstName + " " + user.middleName + " " + user.lastName).also { adminFullNameTextView.text = it }
    }

}