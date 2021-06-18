package org.worldskils.cpu_z.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.worldskils.cpu_z.R
import org.worldskils.cpu_z.data.InfoData
import org.worldskils.cpu_z.databinding.ItemInfoBinding

class InfoAdapter : ListAdapter<InfoData, InfoAdapter.ViewHolder>(InfoDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemInfoBinding>(layoutInflater, viewType, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = getItem(position)
        holder.bind(info)
    }

    override fun getItemViewType(position: Int): Int {
        if (position != 0) {
            return R.layout.item_info
        }

        // Todo
        return R.layout.item_info
    }

    inner class ViewHolder(private val binding: ItemInfoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (infoData: InfoData) {
            binding.infoData = infoData

            binding.executePendingBindings()
        }
    }

    companion object InfoDiffUtil: DiffUtil.ItemCallback<InfoData>(){
        override fun areItemsTheSame(oldItem: InfoData, newItem: InfoData): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: InfoData, newItem: InfoData): Boolean {
            return oldItem==newItem
        }
    }


}