package com.aradevs.investigacion_moviles.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aradevs.domain.binnacles.Binnacle
import com.aradevs.investigacion_moviles.databinding.AddBinnacleDialogBinding
import com.c3rberuss.androidutils.base_views.BaseDialogFragment
import java.util.*

/**
 * [AddBinnacleDialog] Dialog that should be displayed when [Binnacle] data is requested
 */
class AddBinnacleDialog : BaseDialogFragment() {

    private lateinit var binding: AddBinnacleDialogBinding
    private lateinit var onTap: (Binnacle) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = AddBinnacleDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            //TODO: Add validations
            //TODO: Open DatePickerDialog on date edit text
            save.setOnClickListener {
                onTap(Binnacle(0, binding.description.text.toString(), Date(), Date()))
                dismiss()
            }
            cancel.setOnClickListener {
                dismiss()
            }
        }
    }


    companion object {
        fun newInstance(onTap: (Binnacle) -> Unit): AddBinnacleDialog {
            return AddBinnacleDialog().apply {
                this.onTap = onTap
            }
        }
    }
}