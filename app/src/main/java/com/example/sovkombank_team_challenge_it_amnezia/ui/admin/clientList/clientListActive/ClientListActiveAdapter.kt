package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListActive
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.sovkombank_team_challenge_it_amnezia.R
//import kotlinx.android.synthetic.main.client_item.view.*
//import java.text.SimpleDateFormat
//import java.util.ArrayList
//
//class ClientListActiveAdapter (activeClientsList: ArrayList<Client>) : RecyclerView.Adapter<ClientListActiveAdapter.ViewHolder>() {
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.client_item, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val v = holder.itemView
//        //val item = activeClientsList[position]
//        v.firstNameTextview.text = item.paymentId
//        v.credentialsTextview.text = item.amount
////        when (item.avatarUrl) {
////            null -> Glide.with(holder.itemView.context)
////                .load(R.drawable.rounded_imageview)
////                .fitCenter()
////                .into(v.adminAvatarProfileImage)
////            else -> Glide.with(holder.itemView.context)
////                .load(item.avatarUrl)
////                .fitCenter()
////                .placeholder(R.drawable.progress_animation)
////                .error(R.drawable.shape_placeholder)
////                .into(v.adminAvatarProfileImage)
////        }
//    }
//
//    override fun getItemCount() = activeClientsList.size
//}