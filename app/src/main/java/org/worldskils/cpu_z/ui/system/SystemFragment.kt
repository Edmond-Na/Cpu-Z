package org.worldskils.cpu_z.ui.system

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.worldskils.cpu_z.BR
import org.worldskils.cpu_z.R
import org.worldskils.cpu_z.databinding.SystemFragmentBinding
import org.worldskils.cpu_z.ui.adapter.recycler.InfoAdapter
import org.worldskils.cpu_z.ui.base.BaseFragment

class SystemFragment : BaseFragment<SystemFragmentBinding, SystemViewModel>() {

    override val viewModelClass: Class<SystemViewModel>
        get() = SystemViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.system_fragment
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