package com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.services.firebaseMessaging.FirebaseMessagingItAmnesiaService
import com.example.sovkombank_team_challenge_it_amnezia.services.firebaseMessaging.FirebaseMessagingItAmnesiaService.Companion.accessDenied
import com.example.sovkombank_team_challenge_it_amnezia.utils.navigateTo
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.coroutines.*
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
        buttonLogOut.setOnClickListener {
            sharedPreferences.accessToken = null
            sharedPreferences.pinCode = null
            accessDenied = false
            findNavController().navigateTo(findNavController(),R.id.action_profileFragment_to_welcomeFragment, true)
        }
        if(accessDenied) waitAccess()
    }

    private fun getData(){
        presenter.getUserInfo()
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
                accessDenied = false
                requireActivity().finish()
            }
            .onNegative { materialDialog, _ ->
                materialDialog.dismiss()
            }.show()
    }


    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    private fun waitAccess() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(5000)
            CoroutineScope(Dispatchers.Main).launch {
                if(accessDenied){
                    waitAccess()
                    // добавить заглушку
                }
                else {
                    getData()
                    accessDenied = false
                    //убрать заглушку
                    coroutineContext.cancel()
                }
            }
        }
    }

}