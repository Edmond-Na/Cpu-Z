package org.worldskils.cpu_z.ui.soc

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.worldskils.cpu_z.ui.base.BaseViewModel
import org.worldskils.cpu_z.utils.SingleLiveEvent
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.lang.Exception

class SocViewModel(application: Application) : BaseViewModel<Any>(application) {

    val processName = MutableLiveData<String>()

    private val data = arrayListOf("/system/bin/cat", "/proc/kmsg")

    fun getCpuInfo() {
        val sb = StringBuffer()
        sb.append("abi: ").append(Build.CPU_ABI).append("\n")
        if (File("/proc/kmsg").exists()) {
            try {
                val br = BufferedReader(FileReader(File("/proc/cpuinfo")))
                while (br.readLine() != null) {
                    sb.append(br.readLine() + "\n")
                }
                if (br != null) {
                    br.close()
                }
                Log.d("CPU_INFO", sb.toString())
            }
            catch (e: IOException) {
                e.printStackTrace()
            }
        }

        Log.d("TAG", Build.CPU_ABI2)
    }


}