package com.rul8let.osagocalculator.ui.adapter.company

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rul8let.osagocalculator.ui.model.CompanySealed
import com.rul8let.osagocalculator.ui.model.CompanySealed.CompanyItem

class CompanyAdapter(private val clickInputItem: (CompanyItem) -> Unit)
    :  ListAdapter<CompanySealed, RecyclerView.ViewHolder>(PricePolicyDiffCallback) {

    private val load = 0
    private val data = 1

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is CompanyItem -> data
            else -> load
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            data->CompanyViewHolder.create(parent)
            else -> {
                CompanyCalculationLoadViewHolder.create(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CompanyViewHolder -> holder.bind(getItem(position) as CompanyItem, clickInputItem)
        }
    }

    object PricePolicyDiffCallback : DiffUtil.ItemCallback<CompanySealed>() {
        override fun areItemsTheSame(oldItem: CompanySealed, newItem: CompanySealed): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: CompanySealed, newItem: CompanySealed): Boolean {
            return oldItem==newItem
        }
    }
}