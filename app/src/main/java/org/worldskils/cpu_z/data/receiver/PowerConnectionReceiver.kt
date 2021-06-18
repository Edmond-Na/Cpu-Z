package org.worldskils.cpu_z.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import org.worldskils.cpu_z.data.BatteryInfo

class PowerConnectionReceiver(private val batteryResultCallBack: BatteryResultCallBack): BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        try {

            val status = intent!!.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            // charging status
            val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL

            // battery Level
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val batteryLevel = ((level.toFloat() / scale.toFloat()) * 100.0f).toInt()

            // battery Voltage
            val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)

            batteryResultCallBack.callDelegate(BatteryInfo(batteryLevel, isCharging, getBatteryHealth(intent), getBatteryChargeStatus(intent), getBatteryTemperature(intent), voltage))
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getBatteryHealth(intent: Intent): String {
        when (intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0)) {
            BatteryManager.BATTERY_HEALTH_COLD -> {
                return "Cold"
            }
            BatteryManager.BATTERY_HEALTH_DEAD -> {
                return "Dead"
            }
            BatteryManager.BATTERY_HEALTH_GOOD -> {
                return "Good"
            }
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> {
                return "Over Heat"
            }
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> {
                return "Over Voltage"
            }
            BatteryManager.BATTERY_HEALTH_UNKNOWN -> {
                return "Unknown"
            }
            else -> {
                return "Unspecified failure"
            }
        }
    }

    private fun getBatteryChargeStatus(intent: Intent): String {
        return when (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)) {
            BatteryManager.BATTERY_PLUGGED_USB -> {
                "USB Port"
            }
            BatteryManager.BATTERY_PLUGGED_AC -> {
                "AC Charger"
            }
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> {
                "Wireless Charger"
            }
            else -> {
                "Discharging"
            }
        }
    }

    private fun getBatteryTemperature(intent: Intent): Float {
        return (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)).toFloat() / 10
    }

    interface BatteryResultCallBack {
        fun callDelegate(batteryInfo: BatteryInfo)
    }
}