package com.rul8let.osagocalculator.ui.screen.price

import android.animation.LayoutTransition
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.PriceCalculationScreenBinding
import com.rul8let.osagocalculator.ui.adapter.coefficient.CoefficientInfoAdapter
import com.rul8let.osagocalculator.ui.adapter.price.PricePolicyAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PriceCalculationFragment : Fragment() {

    private var _binding : PriceCalculationScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel : PriceCalculationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PriceCalculationScreenBinding.inflate(inflater)
        binding.headerLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindInfoCard()
        binding.bindPriceAdapter()

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun PriceCalculationScreenBinding.bindInfoCard(){
        val adapter = CoefficientInfoAdapter()
        coefficientsInfo.coefficientList.adapter = adapter
        coefficientsInfo.coefficientList.layoutManager = LinearLayoutManager(root.context)
        coefficientsInfo.coefficientList.isNestedScrollingEnabled = false

        coefficientsInfo.checkBoxExpand.setOnCheckedChangeListener { _, b ->
            viewModel.changeExpanded(b)
        }

        viewModel.expanded.observe(viewLifecycleOwner){
            coefficientsInfo.coefficientList.isVisible = it
        }

        viewModel.coefficientList.observe(viewLifecycleOwner){
            adapter.submitList(it)
            val text = getString(
                R.string.coefficient_formula,
                it[0].headerValue,
                it[1].headerValue,
                it[2].headerValue,
                it[3].headerValue,
                it[4].headerValue,
                it[5].headerValue
            )

            val styledText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(text)
            }
            coefficientsInfo.formulaText.text = styledText
        }
    }

    private fun PriceCalculationScreenBinding.bindPriceAdapter(){
        val adapter = PricePolicyAdapter()
        priceList.adapter = adapter
        priceList.layoutManager = LinearLayoutManager(root.context)
        priceList.isNestedScrollingEnabled = false

        viewModel.pricePolicy.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}