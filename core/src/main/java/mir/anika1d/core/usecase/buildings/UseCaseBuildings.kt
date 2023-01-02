package mir.anika1d.core.usecase.buildings

import mir.anika1d.core.retrofit.repository.IRepositoryRetrofit
import mir.anika1d.core.retrofit.repository.RepositoryRetrofitImpl
//RepositoryRetrofitImpl
class UseCaseBuildings(val repository: IRepositoryRetrofit) {
    suspend fun get() = repository.getBuildings()
}