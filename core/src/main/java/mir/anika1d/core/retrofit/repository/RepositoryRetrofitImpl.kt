package mir.anika1d.core.retrofit.repository

import mir.anika1d.core.data.response.BuildingsResponse
import mir.anika1d.core.retrofit.RetrofitServices
import mir.anika1d.handlenetworkexception.tools.result.Result

class RepositoryRetrofitImpl (val retrofitServices: RetrofitServices):IRepositoryRetrofit {
    override suspend fun getBuildings(): Result<BuildingsResponse> =retrofitServices.getBuildings()
}