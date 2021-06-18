package org.worldskils.cpu_z.ui.app.system

import android.app.Application
import android.content.pm.ApplicationInfo
import org.worldskils.cpu_z.data.InfoData
import org.worldskils.cpu_z.ui.base.BaseViewModel


class SystemAppViewModel(application: Application) : BaseViewModel<Any>(application) {
    private val context = getApplication<Application>().applicationContext

    private val pm = context.packageManager
    private val appList = pm.getInstalledApplications(0)

    fun getSystemAppList(): List<InfoData> {
        val systemAppList = mutableListOf<InfoData>()
        for (app in appList) {
            if (app.flags and (ApplicationInfo.FLAG_UPDATED_SYSTEM_APP or ApplicationInfo.FLAG_SYSTEM) > 0) {
                // System App
                systemAppList.add(InfoData(app.loadLabel(pm).toString(), ""))
            }
        }

        return systemAppList
    }
}