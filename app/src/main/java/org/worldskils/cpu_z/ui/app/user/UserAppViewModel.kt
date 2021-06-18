package org.worldskils.cpu_z.ui.app.user

import android.app.Application
import android.content.pm.ApplicationInfo
import androidx.lifecycle.ViewModel
import org.worldskils.cpu_z.data.InfoData
import org.worldskils.cpu_z.ui.base.BaseViewModel

class UserAppViewModel(application: Application) : BaseViewModel<Any>(application) {
    private val context = getApplication<Application>().applicationContext

    private val pm = context.packageManager
    private val appList = pm.getInstalledApplications(0)

    fun getUserAppList(): List<InfoData> {
        val systemAppList = mutableListOf<InfoData>()
        for (app in appList) {
            if (app.flags and (ApplicationInfo.FLAG_UPDATED_SYSTEM_APP or ApplicationInfo.FLAG_SYSTEM) <= 0) {
                // User App
                systemAppList.add(InfoData(app.loadLabel(pm).toString(), ""))
            }
        }

        return systemAppList
    }
}