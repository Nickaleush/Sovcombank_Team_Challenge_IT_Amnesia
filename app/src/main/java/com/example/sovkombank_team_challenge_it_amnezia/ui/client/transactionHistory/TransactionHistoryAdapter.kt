package com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.TransactionDTO
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomeFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory.TransactionHistoryFragment.Companion.chipSelected
import kotlinx.android.synthetic.main.item_home_account.view.*
import kotlinx.android.synthetic.main.transaction_history_item.view.*
import java.math.BigDecimal
import java.math.RoundingMode

class TransactionHistoryAdapter(private val transactionHistoryList: MutableList<TransactionDTO>) :
    RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>(), Filterable {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    var transactionHistoryFilterList = transactionHistoryList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.itemView
        val item = transactionHistoryFilterList[position]
        val accountSrcNumber = item.srcAccount.accountNumber
        val shortAccountSrcNumber = v.context.getString(R.string.From) + accountSrcNumber.substring(14)
        val accountDstNumber = item.dstAccount?.accountNumber
        val shortAccountDstNumber = v.context.getString(R.string.To) + accountDstNumber?.substring(14)
        if(item.dstAccount!=null) {
            v.accountDestTextview.visibility = View.VISIBLE
            v.rubleTextView.visibility = View.VISIBLE
            when (item.dstAccount?.currency?.name) {
                "RUB" -> v.rubleTextView.text = v.context.getText(R.string.Ruble)
                "EUR" -> v.rubleTextView.text = v.context.getText(R.string.Euro)
                "USD" -> v.rubleTextView.text = v.context.getText(R.string.Dollar)
                "CNY" -> v.rubleTextView.text = v.context.getText(R.string.Yuan)
                "GBP" -> v.rubleTextView.text = v.context.getText(R.string.Sterling)
                else -> v.rubleTextView.text = null
            }
            v.accountSrcTextview.text = shortAccountSrcNumber
            v.accountDestTextview.text = shortAccountDstNumber
            v.shortСurrencyNameTextView.text = item.dstAccount?.currency?.name
            val amount = item.amount
            val balance = amount.divide(BigDecimal(100.0), 2,  RoundingMode.FLOOR)
            v.currencyCostTextView.text = balance.toString()
        } else {
            v.accountDestTextview.visibility = View.GONE
            v.rubleTextView.visibility = View.VISIBLE
            when (item.dstAccount?.currency?.name) {
                "RUB" -> v.rubleTextView.text = v.context.getText(R.string.Ruble)
                "EUR" -> v.rubleTextView.text = v.context.getText(R.string.Euro)
                "USD" -> v.rubleTextView.text = v.context.getText(R.string.Dollar)
                "CNY" -> v.rubleTextView.text = v.context.getText(R.string.Yuan)
                "GBP" -> v.rubleTextView.text = v.context.getText(R.string.Sterling)
                else -> v.rubleTextView.text = null
            }
            v.shortСurrencyNameTextView.text = item.srcAccount.currency.name
            v.accountSrcTextview.text = item.srcAccount.accountNumber.substring(14)
            val amount = item.amount
            val balance = amount.divide(BigDecimal(100.0), 2,  RoundingMode.FLOOR)
            v.currencyCostTextView.text = balance.toString()
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


    override fun getItemCount() = transactionHistoryFilterList.size
    
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    transactionHistoryFilterList = transactionHistoryList as ArrayList<TransactionDTO>
                } else {
                    val resultList = ArrayList<TransactionDTO>()
                    for (row in transactionHistoryList) {
                        if(chipSelected=="ALL"){
                            resultList.add(row)
                        }
                        else if(row.type==chipSelected) {
                            resultList.add(row)
                        }
                    }
                    transactionHistoryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = transactionHistoryFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                transactionHistoryFilterList = results?.values as MutableList<TransactionDTO>
                notifyDataSetChanged()
            }
        }
    }

}
