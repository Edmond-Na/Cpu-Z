package org.worldskils.cpu_z.ui.soc

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
import org.worldskils.cpu_z.databinding.SocFragmentBinding
import org.worldskils.cpu_z.ui.base.BaseFragment

class SocFragment : BaseFragment<SocFragmentBinding, SocViewModel>() {

    override val viewModelClass: Class<SocViewModel>
        get() = SocViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.soc_fragment
    }

    override fun getBindingVariable(): Int {
        return BR.vm
    }

    override fun setUp() {
        viewModel.getCpuInfo()
    }

    override fun observerViewModel() {

    }

}