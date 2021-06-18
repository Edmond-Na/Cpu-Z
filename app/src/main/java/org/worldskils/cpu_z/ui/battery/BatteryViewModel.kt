package org.worldskils.cpu_z.ui.battery

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.worldskils.cpu_z.data.BatteryInfo
import org.worldskils.cpu_z.data.InfoData
import org.worldskils.cpu_z.ui.base.BaseViewModel

class BatteryViewModel(application: Application) : BaseViewModel<Any>(application) {

    val batteryInfoData = MutableLiveData<BatteryInfo>()
    val batteryInfoList = mutableListOf<InfoData>()

    fun getBatteryInfo(): List<InfoData> {
        batteryInfoList.clear()

        batteryInfoList.add(InfoData("Health", batteryInfoData.value!!.batteryHealth))
        batteryInfoList.add(InfoData("Level", batteryInfoData.value!!.batteryLevel.toString()))
        batteryInfoList.add(InfoData("Power Source", batteryInfoData.value!!.batteryChargeStatus))
        batteryInfoList.add(InfoData("Status", batteryInfoData.value!!.isCharging.toString()))
        batteryInfoList.add(InfoData("Temperature", batteryInfoData.value!!.batteryTemperature.toString() + "C"))
        batteryInfoList.add(InfoData("Voltage", batteryInfoData.value!!.batteryVoltage.toString() + " mV"))

        return batteryInfoList
    }

}