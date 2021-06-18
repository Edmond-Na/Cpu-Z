package org.worldskils.cpu_z.ui.thermal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.worldskils.cpu_z.R
import org.worldskils.cpu_z.databinding.ThermalFragmentBinding
import org.worldskils.cpu_z.ui.base.BaseFragment

class ThermalFragment : BaseFragment<ThermalFragmentBinding, ThermalViewModel>() {

    override val viewModelClass: Class<ThermalViewModel>
        get() = ThermalViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.thermal_fragment
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun setUp() {

    }

    override fun observerViewModel() {

    }

}