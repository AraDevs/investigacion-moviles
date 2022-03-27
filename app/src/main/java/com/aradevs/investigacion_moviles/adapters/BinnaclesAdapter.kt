package com.aradevs.investigacion_moviles.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aradevs.domain.binnacles.Binnacle
import com.aradevs.investigacion_moviles.databinding.BinnacleItemBinding
import com.c3rberuss.androidutils.toDayMonthYearHour

/**
 * [BinnaclesAdapter] Adapter for any [Binnacle] list recyclerview
 */
class BinnaclesAdapter :
    ListAdapter<Binnacle, BinnaclesAdapter.BinnacleViewHolder>(diffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinnacleViewHolder {
        return BinnacleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            BinnacleViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: BinnacleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BinnacleViewHolder(private val binding: BinnacleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(binnacle: Binnacle) {
            with(binding) {
                description.text = binnacle.description
                dateRange.text =
                    "${binnacle.initDate.toDayMonthYearHour()} - ${binnacle.endDate.toDayMonthYearHour()}"
            }
        }
    }
}

private val diffUtils = object : DiffUtil.ItemCallback<Binnacle>() {
    override fun areItemsTheSame(oldItem: Binnacle, newItem: Binnacle): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Binnacle, newItem: Binnacle): Boolean {
        return oldItem == newItem
    }

}