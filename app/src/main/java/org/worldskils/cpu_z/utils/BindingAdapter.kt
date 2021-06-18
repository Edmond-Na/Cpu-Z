package org.worldskils.cpu_z.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.worldskils.cpu_z.data.BatteryInfo
import org.worldskils.cpu_z.data.InfoData
import org.worldskils.cpu_z.ui.adapter.recycler.InfoAdapter

object BindingAdapter {
    @BindingAdapter("listData")
    @JvmStatic
    fun bindData(recyclerView: RecyclerView, infoDatas: List<InfoData>?) {
        val adapter = recyclerView.adapter as InfoAdapter
        adapter.submitList(infoDatas)
    }
}