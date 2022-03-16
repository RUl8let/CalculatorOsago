package com.rul8let.osagocalculator.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.BottomCardInputBinding
import com.rul8let.osagocalculator.ui.InfoInputEnum
import com.rul8let.osagocalculator.ui.model.InputInfoItem
import com.rul8let.osagocalculator.ui.screen.calculator.CalculatorViewModel

class BottomInputFragment : BottomSheetDialogFragment() {

    private var _binding : BottomCardInputBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CalculatorViewModel by hiltNavGraphViewModels(R.id.osago_nav)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (dialog is BottomSheetDialog) {
            dialog.behavior.skipCollapsed = true
            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomCardInputBinding.inflate(inflater)
        binding.root.minHeight = resources.displayMetrics.heightPixels/2
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectInput.observe(viewLifecycleOwner){
            val inputInfo = viewModel.inputInfoList.value?.get(it.ordinal)
            binding.bindData(inputInfo!!)
        }

        binding.buttonClose.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonNext.setOnClickListener {
            viewModel.updateInputText(binding.editTextInfo.text.toString())
            viewModel.nextInputType()
        }

        binding.buttonBack.setOnClickListener {
            viewModel.updateInputText(binding.editTextInfo.text.toString())
            viewModel.backInputType()
        }
    }

    private fun BottomCardInputBinding.bindData(inputInfo: InputInfoItem) {
        textInfo.text = getString(inputInfo.type.stringResId)
        editTextInfo.setText(inputInfo.texts)
        editTextInfo.inputType = inputInfo.type.inputType
        editTextInfo.selectAll()
        editTextInfo.requestFocus()
        buttonBack.isVisible = inputInfo.type.ordinal!=0

        val visibilityButtonNext = viewModel.selectInput.value?.ordinal!=InfoInputEnum.values().size-1
        buttonNext.isVisible = visibilityButtonNext
        buttonClose.isVisible = !visibilityButtonNext
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateInputText(binding.editTextInfo.getText().toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}