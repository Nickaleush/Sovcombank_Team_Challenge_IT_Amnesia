package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientNotConfirmedList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Client
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ClientDTO
import kotlinx.android.synthetic.main.client_item.view.*
import kotlinx.android.synthetic.main.client_item.view.firstNameTextview
import kotlinx.android.synthetic.main.client_not_confirmed_item.view.*
import java.util.ArrayList

class ClientListNotConfirmedAdapter(private val notConfirmedClientsList: ArrayList<ClientDTO>, val fragment: ClientListNotConfirmedFragment) : RecyclerView.Adapter<ClientListNotConfirmedAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.client_not_confirmed_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.itemView
        val item = notConfirmedClientsList[position]
        v.fullNameTextview.text = item.fullName
        v.phoneNumberTextview.text = item.credentials
        v.buttonConfirmed.setOnClickListener { fragment.startConfirmClient(item.id, position) }
//        when (item.avatarUrl) {
//            null -> Glide.with(holder.itemView.context)
//                .load(R.drawable.rounded_imageview)
//                .fitCenter()
//                .into(v.adminAvatarProfileImage)
//            else -> Glide.with(holder.itemView.context)
//                .load(item.avatarUrl)
//                .fitCenter()
//                .placeholder(R.drawable.progress_animation)
//                .error(R.drawable.shape_placeholder)
//                .into(v.adminAvatarProfileImage)
//        }
    }

    override fun getItemCount() = notConfirmedClientsList.size
}