package org.worldskils.cpu_z.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.worldskils.cpu_z.ui.app.user.UserAppViewModel
import org.worldskils.cpu_z.ui.battery.BatteryViewModel
import org.worldskils.cpu_z.ui.device.DeviceViewModel
import org.worldskils.cpu_z.ui.main.MainViewModel
import org.worldskils.cpu_z.ui.sensors.SensorsViewModel
import org.worldskils.cpu_z.ui.soc.SocViewModel
import org.worldskils.cpu_z.ui.system.SystemViewModel
import org.worldskils.cpu_z.ui.thermal.ThermalViewModel


val viewModelModule = module {
    viewModel { BatteryViewModel(get()) }
    viewModel { DeviceViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { SensorsViewModel(get()) }
    viewModel { SocViewModel(get()) }
    viewModel { SystemViewModel(get()) }
    viewModel { ThermalViewModel(get()) }
    viewModel { UserAppViewModel(get()) }
    viewModel { SystemViewModel(get()) }
    viewModel { BatteryViewModel(get()) }
}
//
//val remoteModule = module {
//    single { RemoteClient }
//}
//
//val dataSourceModule = module {
//    single { FileSourceImpl(get()) as FileDataSource }
//}
//
//val apiModule = module {
//    single { fileAPI }
//}
//
//val repositoryModule = module {
//    single { FileRepositoryImpl(get()) as FileRepository }
//}
//
//val useCaseModule = module {
//    single { FileUseCase(get()) }
//}
//
//val retrofit = RemoteClient.createRetrofit(true)
//private val fileAPI = retrofit.create(FileService::class.java)

val appModules = listOf(
    viewModelModule
//    remoteModule,
//    dataSourceModule,
//    apiModule,
//    repositoryModule,
//    useCaseModule
)