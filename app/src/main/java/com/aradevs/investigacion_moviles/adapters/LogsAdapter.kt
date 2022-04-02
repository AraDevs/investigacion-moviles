package com.aradevs.investigacion_moviles.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aradevs.domain.logs.Log
import com.aradevs.investigacion_moviles.databinding.LogItemBinding
import com.c3rberuss.androidutils.toDayMonthYearHour

/**
 * [LogsAdapter] Adapter for any [Log] list recyclerview
 */
class LogsAdapter :
    ListAdapter<Log, LogsAdapter.BinnacleViewHolder>(diffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinnacleViewHolder {
        return LogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            BinnacleViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: BinnacleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BinnacleViewHolder(private val binding: LogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(log: Log) {
            with(binding) {
                description.text = log.description
                dateRange.text =
                    "${log.initDate.toDayMonthYearHour()} - ${log.endDate.toDayMonthYearHour()}"
            }
        }
    }
}

private val diffUtils = object : DiffUtil.ItemCallback<Log>() {
    override fun areItemsTheSame(oldItem: Log, newItem: Log): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Log, newItem: Log): Boolean {
        return oldItem == newItem
    }

}