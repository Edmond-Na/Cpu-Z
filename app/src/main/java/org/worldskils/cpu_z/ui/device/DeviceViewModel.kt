package org.worldskils.cpu_z.ui.device

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.MutableLiveData
import org.worldskils.cpu_z.data.InfoData
import org.worldskils.cpu_z.ui.base.BaseViewModel
import java.io.File
import java.text.DecimalFormat
import kotlin.concurrent.timer
import kotlin.math.*

class DeviceViewModel(application: Application) : BaseViewModel<Any>(application) {

    private val deviceInfoData = mutableListOf<InfoData>()
    private val context = getApplication<Application>().applicationContext

    private val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    private val actManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    private val memoryInfo = ActivityManager.MemoryInfo()

    // Device Screen Size
    private fun getScreenSize(): String {
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val x = (dm.widthPixels / dm.xdpi).toDouble().pow(2.0)
        val y = (dm.heightPixels / dm.ydpi).toDouble().pow(2.0)

        val screenInches = sqrt(x + y)

        Log.d("Screen Inch", screenInches.toString())

        return ((screenInches * 100).roundToInt() / 100f).toString()
    }

    // Device Screen Resolution
    private fun getScreenResolution(): String {
        val display = wm.defaultDisplay
        val size = Point()
        display.getRealSize(size)

        return size.x.toString() + "x" + size.y.toString()
    }

    private fun getScreenDensity(): String {
        return (context.resources.displayMetrics.density * 160f).toInt().toString()
    }

    private fun getTotalRam(): String {
        actManager.getMemoryInfo(memoryInfo)
        return (memoryInfo.totalMem / FORMAT_MB).toString()
    }

    private fun getAvailableRam(): String {
        actManager.getMemoryInfo(memoryInfo)
        return (memoryInfo.availMem / FORMAT_MB).toString()
    }

    private fun isExternalMemoryAvailable(): Boolean {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
    }

    private fun getExternalStorageAllMemory(checkVal: Boolean): String {

        return if (isExternalMemoryAvailable()) {
            val statFs = StatFs(Environment.getExternalStorageDirectory().path)
            val blockSize: Long = statFs.blockSizeLong
            val totalBlock: Long = statFs.blockCountLong

            if (checkVal) {
                getFileSizeVal(blockSize * totalBlock)
            }
            else {
                getFileSize(blockSize * totalBlock)
            }

        }
        else {
            getFileSizeVal(0)
        }
    }

    private fun getExternalAvailableMemory(checkVal: Boolean): String {
        return if (isExternalMemoryAvailable()) {
            val file: File = Environment.getExternalStorageDirectory()
            val statFs = StatFs(file.path)
            val blockSize: Long = statFs.blockSizeLong
            val availableBlocks: Long = statFs.availableBlocksLong

            if (checkVal) {
                getFileSizeVal(blockSize * availableBlocks)
            }
            else {
                getFileSize(blockSize * availableBlocks)
            }
        }
        else {
            getFileSizeVal(0)
        }
    }

    private fun getFileSizeVal(size: Long): String {
        if (size <= 0) return "0"

        val units: Array<String> = arrayOf("B", "KB", "MB", "GB", "TB");
        val digitGroups: Int = (log10(size.toDouble()) / log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
    }

    private fun getFileSize(size: Long): String {
        if (size <= 0) return "0"

        val digitGroups: Int = (log10(size.toDouble()) / log10(1024.0)).toInt()
        return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble()))
    }


    fun getDeviceInfoList(): List<InfoData> {
        deviceInfoData.add(InfoData("Model", Build.MODEL))
        deviceInfoData.add(InfoData("Manufacturer", Build.MANUFACTURER))
        deviceInfoData.add(InfoData("Board", Build.BOARD))
        deviceInfoData.add(InfoData("Hardware", Build.HARDWARE))
        deviceInfoData.add(InfoData("Screen Size", getScreenSize() + " inches"))
        deviceInfoData.add(InfoData("Screen Resolution", getScreenResolution() + " pixels"))
        deviceInfoData.add(InfoData("Screen Density", getScreenDensity() + " dpi"))
        deviceInfoData.add(InfoData("Total RAM", getTotalRam() + " MB"))
        deviceInfoData.add(InfoData("Available RAM", getAvailableRam() + " MB"))
        deviceInfoData.add(InfoData("Internal Storage", getExternalStorageAllMemory(true)))
        deviceInfoData.add(InfoData("Available Storage", getExternalAvailableMemory(true)))

        deviceInfoData.add(InfoData("External Memory (SD Card)", isExternalMemoryAvailable().toString()))

        return deviceInfoData
    }
}

private const val FORMAT_MB = 1048576L