package com.aradevs.investigacion_moviles.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aradevs.domain.logs.Log
import com.aradevs.investigacion_moviles.databinding.AddLogDialogBinding
import com.c3rberuss.androidutils.base_views.BaseDialogFragment
import java.util.*

/**
 * [AddLogDialog] Dialog that should be displayed when [Log] data is requested
 */
class AddLogDialog : BaseDialogFragment() {

    private lateinit var binding: AddLogDialogBinding
    private lateinit var onTap: (Log) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = AddLogDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            //TODO: Add validations
            //TODO: Open DatePickerDialog on date edit text
            save.setOnClickListener {
                onTap(Log(0, binding.description.text.toString(), Date(), Date()))
                dismiss()
            }
            cancel.setOnClickListener {
                dismiss()
            }
        }
    }


    companion object {
        fun newInstance(onTap: (Log) -> Unit): AddLogDialog {
            return AddLogDialog().apply {
                this.onTap = onTap
            }
        }
    }
}