package com.rul8let.osagocalculator.ui.adapter.company

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.CompanyItemBinding
import com.rul8let.osagocalculator.ui.model.CompanySealed.CompanyItem
import com.rul8let.osagocalculator.ui.util.MoneyFormat

class CompanyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    private val binding = CompanyItemBinding.bind(itemView)

    fun bind(data: CompanyItem, clickInputItem: (CompanyItem) -> Unit){
        binding.nameOrganization.text = data.name
        binding.rating.text = data.rating
        binding.price.text = binding.root.context.getString(R.string.ruble, MoneyFormat.manyFormat(data.price))

        val color = Color.parseColor("#${data.backgroundColor}")
        binding.cardImage.setCardBackgroundColor(color)

        if(data.UrlSVG != null){
            binding.imageOrganization.load(data.UrlSVG){
                decoderFactory(SvgDecoder.Factory())
            }
        }

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