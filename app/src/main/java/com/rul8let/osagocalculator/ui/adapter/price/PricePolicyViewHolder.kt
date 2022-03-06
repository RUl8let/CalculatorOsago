package com.rul8let.osagocalculator.ui.adapter.price

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.PriceItemBinding
import com.rul8let.osagocalculator.ui.model.PriceCalculation.PriceCalculationItem

class PricePolicyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    private val binding = PriceItemBinding.bind(itemView)

    fun bind(data : PriceCalculationItem){
        binding.nameOrganization.text = data.name
        binding.rating.text = data.rating
        binding.price.text = binding.root.context.getString(R.string.ruble, data.price)

        val color = Color.parseColor("#${data.backgroundColor}")
        binding.cardView.setCardBackgroundColor(color)

        if(data.UrlSVG != null){
            binding.imageOrganization.load(data.UrlSVG){
                decoderFactory(SvgDecoder.Factory())
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup) : PricePolicyViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view= layoutInflater.inflate(R.layout.price_item,parent,false)
            return PricePolicyViewHolder(view)
        }
    }
}