package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Client
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ClientDTO
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.utils.SwipeLeftCallback
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.android.synthetic.main.client_list_active_fragment.*
import kotlinx.android.synthetic.main.client_list_blocked_fragment.*
import java.util.ArrayList

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

        //val list = arrayListOf<Client>(Client("Гайдук Д.А.","+79184463344","", 0), Client("Ушаков Н.А.","+79184343333","", 1), Client("Гайдук Д.А.","+79184463344","", 2))
        //initRecyclerView(list)

        presenter.getBlockedClients()

    }

//    private fun initRecyclerView(blockedClientsList: ArrayList<Client>) {
//        if (blockedClientsList.isEmpty()) {
//            emptyBlockedClientListTextView.visibility = View.VISIBLE
//            emptyBlockedClientListImageView.visibility = View.VISIBLE
//        } else {
//            emptyBlockedClientListTextView.visibility = View.GONE
//            emptyBlockedClientListImageView.visibility = View.GONE
//        }
//        blockedClientRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        val adapter = ClientListBlockedAdapter(blockedClientsList)
//        blockedClientRecyclerView.adapter = adapter
//        setupItemSwipe()
//    }

    override fun initRecyclerViewBlockedClient(blockedClientsList: ArrayList<ClientDTO>) {
        if (blockedClientsList.isEmpty()) {
            emptyBlockedClientListTextView.visibility = View.VISIBLE
            emptyBlockedClientListImageView.visibility = View.VISIBLE
        } else {
            emptyBlockedClientListTextView.visibility = View.GONE
            emptyBlockedClientListImageView.visibility = View.GONE
        }
        blockedClientRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = ClientListBlockedAdapter(blockedClientsList)
        blockedClientRecyclerView.adapter = adapter
        setupItemSwipe()
    }

    private fun setupItemSwipe(){
        val swipeCallback = object : SwipeLeftCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                MaterialDialog.Builder(requireContext())
                    .content(R.string.QuestionBlockedMessage)
                    .positiveText(R.string.Blocked)
                    .positiveColorRes(R.color.red)
                    .onPositive { _, _ ->
                        val position = viewHolder.adapterPosition
                        blockedClientRecyclerView.adapter?.notifyItemRemoved(position) }
                    .negativeText(R.string.Cancel)
                    .negativeColorRes(R.color.blue)
                    .onNegative { dialog, _ ->
                        dialog.dismiss()
                        val position = viewHolder.adapterPosition
                        blockedClientRecyclerView.adapter?.notifyItemChanged(position)
                    }
                    .show()
            }
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
                    .addSwipeLeftLabel(getString(R.string.BlockUser))
                    .setSwipeLeftLabelColor(resources.getColor(R.color.white, null))
                    .create()
                    .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }
        val itemTouchHelperForDelete = ItemTouchHelper(swipeCallback)
        itemTouchHelperForDelete.attachToRecyclerView(blockedClientRecyclerView)
    }

    override fun onBackPressed() {}

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}