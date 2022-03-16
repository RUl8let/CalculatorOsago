package com.rul8let.osagocalculator.ui.adapter.company

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.CompanyItemBinding
import com.rul8let.osagocalculator.ui.binding.bindData
import com.rul8let.osagocalculator.ui.model.CompanySealed.CompanyItem

class CompanyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    private val binding = CompanyItemBinding.bind(itemView)

    fun bind(data: CompanyItem, clickInputItem: (CompanyItem) -> Unit){
        
        binding.bindData(data)

        binding.cardItem.setOnClickListener {
            clickInputItem(data)
        }
    }

    companion object {
        fun create(parent: ViewGroup) : CompanyViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view= layoutInflater.inflate(R.layout.company_item,parent,false)
            return CompanyViewHolder(view)
        }
    }
}