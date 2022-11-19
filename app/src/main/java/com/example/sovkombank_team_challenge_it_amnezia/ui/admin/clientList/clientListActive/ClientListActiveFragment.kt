package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListActive

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
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.utils.SwipeCallback
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
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

        val list = arrayListOf<Client>(Client("Гайдук Д.А.","+79184463344",""), Client("Ушаков Н.А.","+79184343333",""))
        initRecyclerView(list)
    }

    private fun initRecyclerView(activeClientsList: ArrayList<Client>) {
        if (activeClientsList.isEmpty()) {
            emptyActiveClientListTextView.visibility = View.VISIBLE
            emptyActiveClientListImageView.visibility = View.VISIBLE
        } else {
            emptyActiveClientListTextView.visibility = View.GONE
            emptyActiveClientListImageView.visibility = View.GONE
        }
        activeClientRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = ClientListActiveAdapter(activeClientsList)
        activeClientRecyclerView.adapter = adapter
        setupItemSwipe()
    }

    override fun initRecyclerViewActiveClient(activeClientsList: ArrayList<Client>) {
        if (activeClientsList.isEmpty()) {
            emptyActiveClientListTextView.visibility = View.VISIBLE
            emptyActiveClientListImageView.visibility = View.VISIBLE
        } else {
            emptyActiveClientListTextView.visibility = View.GONE
            emptyActiveClientListImageView.visibility = View.GONE
        }
        activeClientRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = ClientListActiveAdapter(activeClientsList)
        activeClientRecyclerView.adapter = adapter
        setupItemSwipe()
    }

    private fun setupItemSwipe(){
        val swipeCallback = object : SwipeCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                MaterialDialog.Builder(requireContext())
                    .content(R.string.QuestionBlockedMessage)
                    .positiveText(R.string.Blocked)
                    .positiveColorRes(R.color.red)
                    .onPositive { dialog, which ->
                        val position = viewHolder.adapterPosition
                        activeClientRecyclerView.adapter?.notifyItemRemoved(position) }
                    .negativeText(R.string.Cancel)
                    .negativeColorRes(R.color.blue)
                    .onNegative { dialog, which ->
                        dialog.dismiss()
                        val position = viewHolder.adapterPosition
                        activeClientRecyclerView.adapter?.notifyItemChanged(position)
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
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
                    .addActionIcon(R.drawable.ic_block_client_foreground)
                    .create()
                    .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }
        val itemTouchHelperForDelete = ItemTouchHelper(swipeCallback)
        itemTouchHelperForDelete.attachToRecyclerView(activeClientRecyclerView)
    }
    override fun onBackPressed() {
        TODO("Not yet implemented")
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}