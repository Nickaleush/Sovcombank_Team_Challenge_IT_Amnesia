package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Client
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ClientDTO
import kotlinx.android.synthetic.main.client_item.view.*
import java.util.ArrayList

class ClientListBlockedAdapter (private val blockedClientsList: MutableList<ClientDTO>) : RecyclerView.Adapter<ClientListBlockedAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.client_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.itemView
        val item = blockedClientsList[position]
        v.firstNameTextview.text = item.fullName
        v.credentialsTextview.text = item.credentials
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

    override fun getItemCount() = blockedClientsList.size
}