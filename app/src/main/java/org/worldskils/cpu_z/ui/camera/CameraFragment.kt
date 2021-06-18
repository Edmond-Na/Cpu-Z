package org.worldskils.cpu_z.ui.camera

import android.hardware.Camera
import org.worldskils.cpu_z.BR
import org.worldskils.cpu_z.R
import org.worldskils.cpu_z.databinding.CameraFragmentBinding
import org.worldskils.cpu_z.ui.adapter.recycler.InfoAdapter
import org.worldskils.cpu_z.ui.base.BaseFragment

class CameraFragment : BaseFragment<CameraFragmentBinding, CameraViewModel>() {

    override val viewModelClass: Class<CameraViewModel>
        get() = CameraViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.camera_fragment
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