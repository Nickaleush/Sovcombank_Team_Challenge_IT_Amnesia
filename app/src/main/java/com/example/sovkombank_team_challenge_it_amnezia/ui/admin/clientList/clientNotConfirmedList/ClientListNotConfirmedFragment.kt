package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientNotConfirmedList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Client
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ClientDTO
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked.ClientListBlockedAdapter
import kotlinx.android.synthetic.main.client_list_blocked_fragment.*
import kotlinx.android.synthetic.main.client_list_not_confirmed_fragment.*
import java.util.ArrayList


class ClientListNotConfirmedFragment: BaseFragment<ClientListNotConfirmedPresenterImpl>(),
    ClientListNotConfirmedView {

    var listNotConfirmedClients: MutableList<ClientDTO> = mutableListOf()

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createClientListNotConfirmedFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.client_list_not_confirmed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        presenter.getNotConfirmedClients()

    }

    override fun initRecyclerViewNotConfirmedClient(notConfirmedClientsList: ArrayList<ClientDTO>) {
        listNotConfirmedClients = notConfirmedClientsList
        if (notConfirmedClientsList.isEmpty()) {
            emptyNotConfirmedClientListTextView.visibility = View.VISIBLE
            emptyNotConfirmedClientListImageView.visibility = View.VISIBLE
        } else {
            emptyNotConfirmedClientListTextView.visibility = View.GONE
            emptyNotConfirmedClientListImageView.visibility = View.GONE
        }
        notConfirmedClientRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = ClientListNotConfirmedAdapter(listNotConfirmedClients, this)
        notConfirmedClientRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.getNotConfirmedClients()
    }

    override fun endConfirmClient() {
        listNotConfirmedClients.removeAt(clickedItemPosition)
        notConfirmedClientRecyclerView.adapter?.notifyItemRemoved(clickedItemPosition)
    }

    fun startConfirmClient(id: String, position: Int){
        clickedItemPosition = position
        presenter.confirmClient(id)
    }
    override fun onBackPressed() {}

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    companion object{
        var clickedItemPosition= -1
    }

}