package mir.anika1d.fefumap.koin

import mir.anika1d.connectivityobserver.NetworkConnectivityObserver
import org.koin.dsl.module

val networkConnectivityModule = module {
    single { NetworkConnectivityObserver(context = get()) }
}