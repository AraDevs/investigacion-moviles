package com.aradevs.investigacion_moviles.dialogs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.aradevs.domain.logs.Log
import com.aradevs.investigacion_moviles.R
import com.aradevs.investigacion_moviles.databinding.AddLogDialogBinding
import com.c3rberuss.androidutils.base_views.BaseDialogFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

/**
 * [AddLogDialog] Dialog that should be displayed when [Log] data is requested
 */
class AddLogDialog : BaseDialogFragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: AddLogDialogBinding
    private lateinit var onTap: (Log) -> Unit
    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var dateDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog
    private var isInitDate = false

    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("dd-MM-yyyy HH:mm")

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
            setupCalendar()
            setupTime()

            save.setOnClickListener {
                try {
                    if (validator()) {
                        onTap(Log(0,
                            binding.description.text.toString(),
                            format.parse(binding.initDate.text.toString())!!,
                            format.parse(binding.endDate.text.toString())!!))
                        dismiss()
                    } else {
                        Snackbar.make(binding.root,
                            getString(R.string.validation_error_add_log),
                            BaseTransientBottomBar.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Snackbar.make(binding.root,
                        getString(R.string.parse_error_add_log),
                        BaseTransientBottomBar.LENGTH_SHORT).show()
                }
            }
            cancel.setOnClickListener {
                dismiss()
            }

            initDate.setOnClickListener {
                isInitDate = true
                dateDialog.show()
            }
            endDate.setOnClickListener {
                isInitDate = false
                dateDialog.show()
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

    private fun validator(): Boolean {
        return when {
            binding.description.text.isNullOrEmpty() -> false
            binding.initDate.text.isNullOrEmpty() -> false
            binding.endDate.text.isNullOrEmpty() -> false
            else -> true
        }
    }

    private fun setupCalendar() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONDAY) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        dateDialog = DatePickerDialog(requireContext(), this, year, month - 1, day)
        dateDialog.datePicker.minDate = calendar.time.time
    }

    private fun setupTime() {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        timePickerDialog = TimePickerDialog(requireContext(), this, hour, minute, true)
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val currentMonth = month + 1
        if (isInitDate) {
            binding.initDate.setText("$day-$currentMonth-$year")
        } else {
            binding.endDate.setText("$day-$currentMonth-$year")
        }
        timePickerDialog.show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {

        when (val currentMinute = if (minute < 10) "0$minute" else minute) {
            0 -> {
                if (isInitDate) {
                    binding.initDate.setText("${binding.initDate.text} $hour:00")
                } else {
                    binding.endDate.setText("${binding.endDate.text} $hour:00")
                }
            }

            else -> {
                if (isInitDate) {
                    binding.initDate.setText("${binding.initDate.text} $hour:$currentMinute")
                } else {
                    binding.endDate.setText("${binding.endDate.text} $hour:$currentMinute")
                }
            }
        }

    }
}