package org.worldskils.cpu_z.ui.camera

import android.app.Application
import android.hardware.Camera
import androidx.lifecycle.ViewModel
import org.worldskils.cpu_z.data.InfoData
import org.worldskils.cpu_z.ui.base.BaseViewModel
import java.lang.StringBuilder

class CameraViewModel(application: Application) : BaseViewModel<Any>(application) {
    private val camera = Camera.open()
    private val parameters = camera.parameters

    private val cameraInfoData = mutableListOf<InfoData>()
//    private val context = getApplication<Application>().applicationContext


    fun getDeviceInfoList(): List<InfoData> {
        cameraInfoData.add(InfoData("Support Anti Banding", listToLineString(parameters.supportedAntibanding)))
        cameraInfoData.add(InfoData("Support Color Effects", listToLineString(parameters.supportedColorEffects)))
        cameraInfoData.add(InfoData("Support Focus Modes", listToLineString(parameters.supportedFocusModes)))
        cameraInfoData.add(InfoData("Support Flash Modes", listToLineString(parameters.supportedFlashModes)))
        cameraInfoData.add(InfoData("Current Preview Size", cameraSizeToString(parameters.previewSize)))
        cameraInfoData.add(InfoData("Current Focus Mode", parameters.focusMode))
        cameraInfoData.add(InfoData("Current Flash Mode", parameters.flashMode))
        cameraInfoData.add(InfoData("Max Zoom", parameters.maxZoom.toString() + "X"))

        return cameraInfoData
    }

    private fun cameraSizeToString(size: Camera.Size): String {
        return size.width.toString() + "x" + size.height.toString()
    }

    private fun listToLineString(listData: List<String>): String {
        val strBuilder = StringBuilder()

        for (i in listData.indices) {
            strBuilder.append(listData[i] + "\n")
        }

        return strBuilder.toString()
    }
}