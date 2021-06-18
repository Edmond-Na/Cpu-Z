package org.worldskils.cpu_z.ui.battery

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import org.worldskils.cpu_z.BR
import org.worldskils.cpu_z.R
import org.worldskils.cpu_z.data.BatteryInfo
import org.worldskils.cpu_z.data.receiver.PowerConnectionReceiver
import org.worldskils.cpu_z.databinding.BatteryFragmentBinding
import org.worldskils.cpu_z.ui.adapter.recycler.InfoAdapter
import org.worldskils.cpu_z.ui.base.BaseFragment

class BatteryFragment : BaseFragment<BatteryFragmentBinding, BatteryViewModel>() {

    private val adapter = InfoAdapter()

    override val viewModelClass: Class<BatteryViewModel>
        get() = BatteryViewModel::class.java

    private lateinit var powerConnectionReceiver : BroadcastReceiver

    override fun getLayoutId(): Int {
        return R.layout.battery_fragment
    }

    override fun getBindingVariable(): Int {
        return BR.vm
    }

    override fun setUp() {
        powerConnectionReceiver = PowerConnectionReceiver(object :
            PowerConnectionReceiver.BatteryResultCallBack {
            override fun callDelegate(
                batteryInfo: BatteryInfo
            ) {
                viewModel.batteryInfoData.value = batteryInfo
                viewModel.getBatteryInfo()
            }
        })
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        requireActivity().registerReceiver(powerConnectionReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()

        requireActivity().unregisterReceiver(powerConnectionReceiver)
    }

    override fun observerViewModel() {
        with(viewModel) {
            batteryInfoData.observe(this@BatteryFragment, {
                adapter.submitList(viewModel.batteryInfoList)
                binding.recyclerView.adapter = adapter
            })
        }
    }


}