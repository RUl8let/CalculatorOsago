package com.rul8let.osagocalculator.ui.screen.price

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rul8let.osagocalculator.databinding.PriceCalculationScreenBinding
import com.rul8let.osagocalculator.ui.adapter.company.CompanyAdapter
import com.rul8let.osagocalculator.ui.binding.bindCoefficientCard
import com.rul8let.osagocalculator.ui.model.CompanySealed.CompanyItem
import com.rul8let.osagocalculator.ui.screen.calculator.CalculatorFragment
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
        binding.bindPriceAdapter()
        binding.coefficientsInfo.bindCoefficientCard(
            viewModel.expandedCoefficientCard,
            viewModel.coefficientList,
            viewLifecycleOwner) { expand->
            viewModel.changeExpanded(expand)
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun PriceCalculationScreenBinding.bindPriceAdapter(){
        val adapter = CompanyAdapter(clickInputItem)
        priceList.adapter = adapter
        priceList.layoutManager = LinearLayoutManager(root.context)
        priceList.isNestedScrollingEnabled = false

        viewModel.pricePolicy.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private val clickInputItem = fun (data : CompanyItem){
        findNavController().previousBackStackEntry?.
            savedStateHandle?.set(CalculatorFragment.keySelectItemPrice, data)
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}