package com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.TransactionDTO
import kotlinx.android.synthetic.main.transaction_history_item.view.*

class TransactionHistoryAdapter(private val transactionHistoryList: MutableList<TransactionDTO>) :
    RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.itemView
        val item = transactionHistoryList[position]
        if((item.dstAccount.id).toString()!=""){
            v.accountDestTextview.visibility = View.VISIBLE
            v.rubleTextView.visibility = View.VISIBLE
            v.accountSrcTextview.text = item.srcAccount.id.toString()
            v.accountDestTextview.text = item.dstAccount.id.toString()
            v.shortСurrencyNameTextView.text = item.dstAccount.currency.name
            item.srcAccount.amount.toString().also { v.currencyCostTextView.text = it }
        }else{
            v.accountDestTextview.visibility = View.GONE
            v.rubleTextView.visibility = View.VISIBLE
            v.shortСurrencyNameTextView.text = item.srcAccount.currency.name
            v.accountSrcTextview.text = item.srcAccount.id.toString()
            item.srcAccount.amount.toString().also { v.currencyCostTextView.text = it }

        }
        when (item.type) {
            "RECHARGE" -> v.typeTransactionTextview.setText(R.string.Recharge)
            "TRANSACTION" -> v.typeTransactionTextview.setText(R.string.Transaction)
            "WITHDRAWAL" -> v.typeTransactionTextview.setText(R.string.Withdrawal)
        }


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

    override fun getItemCount() = transactionHistoryList.size
}