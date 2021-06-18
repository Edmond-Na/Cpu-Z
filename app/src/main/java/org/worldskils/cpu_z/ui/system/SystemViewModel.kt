package org.worldskils.cpu_z.ui.system

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.os.Build
import android.os.SystemClock
import android.util.Log
import androidx.core.content.pm.PackageInfoCompat
import androidx.lifecycle.ViewModel
import com.google.android.gms.common.GoogleApiAvailability
import com.lahm.library.EasyProtectorLib
import org.worldskils.cpu_z.BuildConfig
import org.worldskils.cpu_z.data.InfoData
import org.worldskils.cpu_z.ui.base.BaseViewModel

class SystemViewModel(application: Application) : BaseViewModel<Any>(application) {

    private val systemInfoData = mutableListOf<InfoData>()
    private val context = getApplication<Application>().applicationContext

    private fun getJavaVMVersion(): String {
        val javaVMVersion: String? = System.getProperty("java.vm.version")
        if (javaVMVersion != null) {
            return "ART $javaVMVersion"
        }

        return "Not Found"
    }

    private fun getOpenGLESVersion(): String {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val configurationInfo = activityManager.deviceConfigurationInfo

        return configurationInfo.glEsVersion.toDouble().toString()
    }

    private fun getRootAccess(): String {
        return if (EasyProtectorLib.checkIsRoot()) {
            "Yes"
        }
        else {
            "No"
        }
    }

    private fun getSystemUptime(): String {
        val timeMilliSec = SystemClock.uptimeMillis()

        val sec = timeMilliSec / 1000
        val min = sec / 60
        val hour = min / 60
        val days = hour / 24

        if (days.toInt() == 0) {
            return (hour % 24).toString() + ":" + min % 60 + ":" + sec % 60
        }
        return days.toString() + "days, " + (hour % 24).toString() + ":" + min % 60 + ":" + sec % 60
    }


    fun getSystemInfoList(): List<InfoData> {
        systemInfoData.add(InfoData("Android Version", Build.VERSION.RELEASE))
        systemInfoData.add(InfoData("API Version", Build.VERSION.SDK_INT.toString()))
        systemInfoData.add(InfoData("Security Patch Level", Build.VERSION.SECURITY_PATCH))
        systemInfoData.add(InfoData("Bootloader", Build.BOOTLOADER))
        systemInfoData.add(InfoData("Build ID", Build.ID))
        systemInfoData.add(InfoData("Java VM", getJavaVMVersion()))
        systemInfoData.add(InfoData("OpenGL ES", getOpenGLESVersion()))
        systemInfoData.add(InfoData("Kernel Architecture", System.getProperty("os.arch")))
        systemInfoData.add(InfoData("Kernel Version", System.getProperty("os.version")))
        systemInfoData.add(InfoData("Root Access", getRootAccess()))
        systemInfoData.add(InfoData("Google Play Services", PackageInfoCompat.getLongVersionCode(context.packageManager.getPackageInfo(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE, 0)).toString()))
        systemInfoData.add(InfoData("System Uptime", getSystemUptime()))

        return systemInfoData
    }
}