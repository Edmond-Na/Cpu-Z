package org.worldskils.cpu_z.ui.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.worldskils.cpu_z.ui.app.system.SystemAppFragment
import org.worldskils.cpu_z.ui.app.user.UserAppFragment
import org.worldskils.cpu_z.ui.battery.BatteryFragment
import org.worldskils.cpu_z.ui.camera.CameraFragment
import org.worldskils.cpu_z.ui.device.DeviceFragment
import org.worldskils.cpu_z.ui.sensors.SensorsFragment
import org.worldskils.cpu_z.ui.soc.SocFragment
import org.worldskils.cpu_z.ui.system.SystemFragment
import org.worldskils.cpu_z.ui.thermal.ThermalFragment

class MainFragmentStateAdapter (fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        // // Soc, Device, System, Battery, Thermal, Sensors
        // Device, System, Battery, Sensors
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DeviceFragment()
            1 -> SystemFragment()
            2 -> BatteryFragment()
            3 -> SensorsFragment()
            4 -> CameraFragment()
            5 -> SystemAppFragment()
            6 -> UserAppFragment()
            else -> Fragment()
        }
//        return when (position) {
//            0 -> SocFragment()
//            1 -> DeviceFragment()
//            2 -> SystemFragment()
//            3 -> BatteryFragment()
//            4 -> ThermalFragment()
//            5 -> SensorsFragment()
//            else -> Fragment()
//        }
    }

}