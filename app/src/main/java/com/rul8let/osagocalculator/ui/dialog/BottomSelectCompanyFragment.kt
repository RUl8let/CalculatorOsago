package com.rul8let.osagocalculator.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.BottomCardSelectCompanyBinding
import com.rul8let.osagocalculator.ui.binding.bindCompanyData

class BottomSelectCompanyFragment : BottomSheetDialogFragment() {

    private var _binding : BottomCardSelectCompanyBinding? = null
    private val binding get() = _binding!!

    private val args: BottomSelectCompanyFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

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
            binding.selectCompanyCard.bindCompanyData(data)
        }

        binding.readyButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}