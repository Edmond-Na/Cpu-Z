package org.worldskils.cpu_z.data

data class BatteryInfo(val batteryLevel: Int,
                       val isCharging: Boolean,
                       val batteryHealth: String,
                       val batteryChargeStatus: String,
                       val batteryTemperature: Float,
                       val batteryVoltage: Int)
