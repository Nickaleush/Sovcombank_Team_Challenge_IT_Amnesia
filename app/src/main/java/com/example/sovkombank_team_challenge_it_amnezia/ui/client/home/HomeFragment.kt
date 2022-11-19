package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.HomeButtonType
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ListItemButton
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment.Companion.AUTH_AS_ADMIN
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment: BaseFragment<HomePresenterImpl>(), HomeView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private var homeButtonItems: ArrayList<ListItemButton> = ArrayList()

    private lateinit var homeButtonsAdapter: HomeButtonsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        recyclerViewHomeButtons.layoutManager =  LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        homeButtonsAdapter = HomeButtonsAdapter(homeButtonItems, this@HomeFragment,requireContext())
        recyclerViewHomeButtons.adapter = homeButtonsAdapter
        initializeData()
    }

    private fun initializeData(){
        homeButtonItems.clear()
        homeButtonItems.add(ListItemButton(HomeButtonType.Deposit))
        homeButtonItems.add(ListItemButton(HomeButtonType.Buy))
        homeButtonItems.add(ListItemButton(HomeButtonType.Withdraw))
        homeButtonItems.add(ListItemButton(HomeButtonType.Sell))
        homeButtonItems.add(ListItemButton(HomeButtonType.Statistics))
    }

    fun openDepositSheet() {

    }

    fun openBuySheet() {

    }

    fun openWithDrawSheet() {

    }

    fun openSellSheet() {

    }

    fun openStatisticsFragment() {

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

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createHomeFragment()
            .inject(this)
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}