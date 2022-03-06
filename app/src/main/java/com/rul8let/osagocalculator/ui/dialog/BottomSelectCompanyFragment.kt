package com.rul8let.osagocalculator.ui.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.decode.SvgDecoder
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.BottomCardSelectCompanyBinding
import com.rul8let.osagocalculator.databinding.CompanyItemBinding
import com.rul8let.osagocalculator.ui.model.CompanySealed.CompanyItem
import com.rul8let.osagocalculator.ui.util.MoneyFormat

class BottomSelectCompanyFragment : BottomSheetDialogFragment() {

    private var _binding : BottomCardSelectCompanyBinding? = null
    private val binding get() = _binding!!

    private val args: BottomSelectCompanyFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomCardSelectCompanyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = args.selectCompany
        if (data!=null){
            binding.selectCompanyCard.bindData(data)
        }
    }

    private fun CompanyItemBinding.bindData(data: CompanyItem) {
        nameOrganization.text = data.name
        rating.text = data.rating
        price.text = binding.root.context.getString(R.string.ruble, MoneyFormat.manyFormat(data.price))

        val color = Color.parseColor("#${data.backgroundColor}")
        cardImage.setCardBackgroundColor(color)

        if(data.UrlSVG != null){
            imageOrganization.load(data.UrlSVG){
                decoderFactory(SvgDecoder.Factory())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}