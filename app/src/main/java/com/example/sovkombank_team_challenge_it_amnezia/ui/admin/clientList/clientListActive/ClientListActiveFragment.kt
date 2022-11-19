package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListActive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import kotlinx.android.synthetic.main.client_list_active_fragment.*
import java.util.ArrayList

class ClientListActiveFragment : BaseFragment<ClientListActivePresenterImpl>(),
    ClientListActiveView {


    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createClientListActiveFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.client_list_active_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this

        emptyActiveClientListTextView.visibility = View.VISIBLE
        emptyActiveClientListImageView.visibility = View.VISIBLE
    }

//    override fun initRecyclerViewActiveClient(activeClientsList: ArrayList<Client>) {
//        if (activeClientsList.isEmpty()) {
//            emptyActiveClientListTextView.visibility = View.VISIBLE
//            emptyActiveClientListImageView.visibility = View.VISIBLE
//        } else {
//            emptyActiveClientListTextView.visibility = View.GONE
//            emptyActiveClientListImageView.visibility = View.GONE
//        }
//        activeClientRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        val adapter = ClientListActiveAdapter(activeClientsList)
//        activeClientRecyclerView.adapter = adapter
//    }

    override fun onBackPressed() {
        TODO("Not yet implemented")
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}