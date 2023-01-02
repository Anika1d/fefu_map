package mir.anika1d.fefumap.koin

import mir.anika1d.core.preferenseProvider.PreferenceProviderImpl
import mir.anika1d.core.retrofit.provideOkHttpClientCore
import mir.anika1d.core.retrofit.provideRetrofitCore
import mir.anika1d.core.retrofit.provideRetrofitServicesCore
import mir.anika1d.core.retrofit.repository.RepositoryRetrofitImpl
import org.koin.dsl.module

val retrofitModule = module {
    single { provideRetrofitCore(okHttpClient = get()) }
    single { provideRetrofitServicesCore(retrofit = get()) }
}
val okHttpModule = module {
    single { provideOkHttpClientCore() }
}
val repositoryModule = module {
    single { RepositoryRetrofitImpl(retrofitServices = get()) }
}
val preferenceModule = module {
    single { PreferenceProviderImpl(context = get()) }
}
val useCaseModule = module {
}