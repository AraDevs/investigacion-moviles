package com.aradevs.investigacion_moviles.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aradevs.domain.general.Company
import com.aradevs.investigacion_moviles.databinding.AddCompanyDialogBinding
import com.c3rberuss.androidutils.base_views.BaseDialogFragment
import java.util.*

class AddCompanyDialog : BaseDialogFragment() {

    private lateinit var binding: AddCompanyDialogBinding
    private lateinit var onTap: (Company) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = AddCompanyDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            //TODO: Add validations
            //TODO: Open DatePickerDialog on date edit text
            save.setOnClickListener {
                onTap(Company(0, name.text.toString(), description.text.toString(), Date()))
                dismiss()
            }
        }
    }


    companion object {
        fun newInstance(onTap: (Company) -> Unit): AddCompanyDialog {
            return AddCompanyDialog().apply {
                this.onTap = onTap
            }
        }
    }
}