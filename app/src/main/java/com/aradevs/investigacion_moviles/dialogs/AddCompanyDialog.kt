package com.aradevs.investigacion_moviles.dialogs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.aradevs.domain.general.Company
import com.aradevs.investigacion_moviles.R
import com.aradevs.investigacion_moviles.databinding.AddCompanyDialogBinding
import com.c3rberuss.androidutils.base_views.BaseDialogFragment
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

/**
 * [AddCompanyDialog] Dialog that should be displayed when [Company] data is needed
 */
class AddCompanyDialog : BaseDialogFragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: AddCompanyDialogBinding
    private lateinit var onTap: (Company) -> Unit
    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var dateDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog

    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("dd-MM-yyyy HH:mm")

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
            setupCalendar()
            setupTime()
            save.setOnClickListener {
                try {
                    if (validator()) {
                        onTap(Company(0,
                            name.text.toString(),
                            description.text.toString(),
                            format.parse(binding.initDate.text.toString())!!))
                        dismiss()
                    } else {
                        Snackbar.make(binding.root,
                            getString(R.string.validation_error_add_company),
                            LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Snackbar.make(binding.root,
                        getString(R.string.parse_error_add_company),
                        LENGTH_SHORT).show()
                }
            }
            initDate.setOnClickListener {
                dateDialog.show()
            }
        }
    }

    /**
     * Validates if the required fields are not empty
     */
    private fun validator(): Boolean {
        return when {
            binding.name.text.isNullOrEmpty() -> false
            binding.initDate.text.isNullOrEmpty() -> false
            else -> true
        }
    }

    /**
     * Setting up calendar dialog
     */
    private fun setupCalendar() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONDAY) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        dateDialog = DatePickerDialog(requireContext(), this, year, month - 1, day)
    }

    /**
     * Setting up the time picker dialog
     */
    private fun setupTime() {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        timePickerDialog = TimePickerDialog(requireContext(), this, hour, minute, true)
    }

    /**
     * Listening to the onDateSet method of [DatePickerDialog.setOnDateSetListener] to set the selected date
     * into the specified EditText, this method will inflate the [TimePickerDialog] afterwards
     */
    @SuppressLint("SetTextI18n")
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val currentMonth = month + 1
        binding.initDate.setText("$day-$currentMonth-$year")
        timePickerDialog.show()
    }

    /**
     * Listening to the onTimeSet method of [TimePickerDialog.onTimeChanged] to set the selected time into
     * the specified EditText
     */
    @SuppressLint("SetTextI18n")
    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
        val currentMinute = if (minute < 10) "0$minute" else minute
        if (currentMinute == 0) {
            binding.initDate.setText("${binding.initDate.text} $hour:00")
        } else {
            binding.initDate.setText("${binding.initDate.text} $hour:$currentMinute")
        }
    }

    /**
     * Companion object that receives the onTap method to be executed on [binding] save tap
     */
    companion object {
        fun newInstance(onTap: (Company) -> Unit): AddCompanyDialog {
            return AddCompanyDialog().apply {
                this.onTap = onTap
            }
        }
    }
}