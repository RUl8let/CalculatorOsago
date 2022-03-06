package com.rul8let.osagocalculator.ui.adapter.company

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rul8let.osagocalculator.R

class CompanyCalculationLoadViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup) : CompanyCalculationLoadViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view= layoutInflater.inflate(R.layout.price_load_item,parent,false)
            return CompanyCalculationLoadViewHolder(view)
        }
    }
}