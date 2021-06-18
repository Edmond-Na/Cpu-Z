package org.worldskils.cpu_z.ui.sensors

import android.content.BroadcastReceiver
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
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
import org.worldskils.cpu_z.databinding.SensorsFragmentBinding
import org.worldskils.cpu_z.ui.adapter.recycler.InfoAdapter
import org.worldskils.cpu_z.ui.base.BaseFragment
import java.lang.StringBuilder

class SensorsFragment : BaseFragment<SensorsFragmentBinding,SensorsViewModel>() {

    private val adapter = InfoAdapter()
    private lateinit var sensorManager: SensorManager

    override val viewModelClass: Class<SensorsViewModel>
        get() = SensorsViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.sensors_fragment
    }

    override fun getBindingVariable(): Int {
        return BR.vm
    }

    override fun setUp() {
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        viewModel.getSensorsInfoData()
        adapter.submitList(viewModel.sensorsInfoList)
        binding.recyclerView.adapter = adapter
    }

    override fun observerViewModel() {
        with(viewModel) {
            sensorsInfoData.observe(this@SensorsFragment) {
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(sensorEventListener)
    }

    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            for (i in 0 until viewModel.sensorsInfoList.size) {
                if (viewModel.sensorsInfoList[i].model == event?.sensor?.name && !event.sensor.name.contains("Orientation")) {

                    val strBuilder = StringBuilder()

                    for (j in event.values.indices) {
                        when (j) {
                            0 -> strBuilder.append("X - ")
                            1 -> strBuilder.append("Y - ")
                            2 -> strBuilder.append("Z - ")
                        }

                        strBuilder.append(event.values[j])

                        if (j != event.values.indices.last) strBuilder.append("\n")
                    }

                    viewModel.sensorsInfoList[i].info = strBuilder.toString()
                    viewModel.sensorsInfoData.value = strBuilder.toString()
                }


                else if (viewModel.sensorsInfoList[i].model == event?.sensor?.name && event.sensor.name.contains("Orientation")) {
                    val strBuilder = StringBuilder()

                    for (j in event.values.indices) {
                        when (j) {
                            0 -> strBuilder.append("Azimuth - ")
                            1 -> strBuilder.append("Pitch - ")
                            2 -> strBuilder.append("Roll - ")
                        }

                        strBuilder.append(event.values[j])

                        if (j != event.values.indices.last) strBuilder.append("\n")
                    }

                    viewModel.sensorsInfoList[i].info = strBuilder.toString()
                    viewModel.sensorsInfoData.value = strBuilder.toString()
                }

            }

        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }

    }

}