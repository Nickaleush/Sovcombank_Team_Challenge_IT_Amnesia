package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Account
import kotlinx.android.synthetic.main.item_home_account.view.*

class HomeAccountsAdapter(private val accountsList: ArrayList<Account> ) : RecyclerView.Adapter<HomeAccountsAdapter.ViewHolder>() {

    private lateinit var mListener: OnClickListener

    class ViewHolder(itemView: View, listener: OnClickListener) : RecyclerView.ViewHolder(itemView) {

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_account, parent, false)
        return ViewHolder(view, mListener)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.itemView
        val item = accountsList[position]
        val accountNumber = item.accountNumber
        val shortAccountNumber = accountNumber.substring(14)
        v.accountBalanceTextView.text = item.amount.toString()
        v.accountTypeTextview.text = item.currency.name
        v.billLast4digits.text = shortAccountNumber
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

    fun setOnClickRecyclerListener(listener: OnClickListener){
        mListener = listener
    }

    override fun getItemCount() = accountsList.size
}
