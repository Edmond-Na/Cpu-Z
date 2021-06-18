package org.worldskils.cpu_z.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.tabs.TabLayoutMediator
import org.worldskils.cpu_z.R
import org.worldskils.cpu_z.databinding.ActivityMainBinding
import org.worldskils.cpu_z.ui.adapter.viewpager.MainFragmentStateAdapter
import org.worldskils.cpu_z.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {


    private val tabTextList = arrayListOf("DEVICE", "SYSTEM", "BATTERY", "SENSORS", "CAMERA", "SYSTEM APP", "USER APP")
//    private val tabTextList = arrayListOf("SOC", "DEVICE", "SYSTEM", "BATTERY", "THERMAL", "SENSORS")

    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun setUp() {
        binding.viewpager.adapter = MainFragmentStateAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewpager) {
            tab, position ->
                tab.text = tabTextList[position]
        }.attach()

        if (checkSelfPermission(Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), // 1
                PERMISSION_REQUEST_CODE) // 2
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {  // 1
                if (grantResults.isEmpty()) {  // 2
                    throw RuntimeException("Empty permission result")
                }
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {  // 3
//                    showDialog("Permission granted")
                    Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
                } else {
                    if (shouldShowRequestPermissionRationale(
                            Manifest.permission.CAMERA)) { // 4
                        Log.d("TAG", "User declined, but i can still ask for more")
                        requestPermissions(
                            arrayOf(Manifest.permission.CAMERA),
                            PERMISSION_REQUEST_CODE)
                    } else {
                        Log.d("TAG", "User declined and i can't ask")
                        showDialogToGetPermission()   // 5
                    }
                }
            }
        }
    }

    private fun showDialogToGetPermission() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permisisons request")
            .setMessage("We need the location permission for some reason. " +
                    "You need to move on Settings to grant some permissions")

        builder.setPositiveButton("OK") { dialogInterface, i ->
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", packageName, null))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)   // 6
        }
        builder.setNegativeButton("Later") { dialogInterface, i ->
            // ignore
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun observerViewModel() {

    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 1001

    }
}