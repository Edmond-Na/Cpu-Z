package org.worldskils.cpu_z.ui.device

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.worldskils.cpu_z.BR
import org.worldskils.cpu_z.R
import org.worldskils.cpu_z.databinding.DeviceFragmentBinding
import org.worldskils.cpu_z.ui.adapter.recycler.InfoAdapter
import org.worldskils.cpu_z.ui.base.BaseFragment

class DeviceFragment : BaseFragment<DeviceFragmentBinding, DeviceViewModel>() {

    override val viewModelClass: Class<DeviceViewModel>
        get() = DeviceViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.device_fragment
    }

    override fun getBindingVariable(): Int {
        return BR.vm
    }

    override fun setUp() {
        val adapter = InfoAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun observerViewModel() {

    }


}