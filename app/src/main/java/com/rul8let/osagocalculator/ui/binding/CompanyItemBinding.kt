package com.rul8let.osagocalculator.ui.binding

import android.graphics.Color
import androidx.core.view.isVisible
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.CoefficientsInfoBinding
import com.rul8let.osagocalculator.databinding.CompanyItemBinding
import com.rul8let.osagocalculator.ui.model.CompanySealed.CompanyItem
import com.rul8let.osagocalculator.ui.util.MoneyFormat
import com.rul8let.osagocalculator.ui.util.loadSvg

fun CompanyItemBinding.bindCompanyData(data: CompanyItem){
    nameOrganization.text = data.name
    rating.text = data.rating
    price.text = root.context.getString(R.string.ruble, MoneyFormat.manyFormat(data.price))

    val imageVisible = data.UrlSVG!=null
    imageOrganization.isVisible = imageVisible
    textIconTitle.isVisible = !imageVisible

    if(imageVisible){
        imageOrganization.loadSvg(data.UrlSVG)
    } else {
        val backgroundColor = Color.parseColor("#${data.backgroundColor}")
        val fontColor = Color.parseColor("#${data.fontColor}")
        cardImage.setCardBackgroundColor(backgroundColor)
        textIconTitle.setTextColor(fontColor)
        textIconTitle.text = data.iconTitle
    }
}