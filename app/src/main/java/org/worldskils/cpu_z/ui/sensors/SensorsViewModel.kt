package org.worldskils.cpu_z.ui.sensors

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.worldskils.cpu_z.data.BatteryInfo
import org.worldskils.cpu_z.data.InfoData
import org.worldskils.cpu_z.ui.base.BaseViewModel

class SensorsViewModel(application: Application) : BaseViewModel<Any>(application) {

    val sensorsInfoData = MutableLiveData<String>()
    val sensorsInfoList = mutableListOf<InfoData>()
    private val context = getApplication<Application>().applicationContext

    @JvmName("getSensorsInfoData1")
    fun getSensorsInfoData(): List<InfoData> {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)


        for (i in 0 until sensors.size) {
             sensorsInfoList.add(InfoData(sensors[i].name, ""))
        }

        return sensorsInfoList
    }
}