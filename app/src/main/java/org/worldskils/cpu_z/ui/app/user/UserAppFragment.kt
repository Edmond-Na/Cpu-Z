package org.worldskils.cpu_z.ui.app.user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.worldskils.cpu_z.BR
import org.worldskils.cpu_z.R
import org.worldskils.cpu_z.databinding.UserAppFragmentBinding
import org.worldskils.cpu_z.ui.adapter.recycler.InfoAdapter
import org.worldskils.cpu_z.ui.base.BaseFragment

class UserAppFragment : BaseFragment<UserAppFragmentBinding, UserAppViewModel>() {
    override val viewModelClass: Class<UserAppViewModel>
        get() = UserAppViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.user_app_fragment
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