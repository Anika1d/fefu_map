package mir.anika1d.core.retrofit.repository

import mir.anika1d.core.data.response.BuildingsResponse
import mir.anika1d.handlenetworkexception.tools.result.Result

interface IRepositoryRetrofit:IMapRepository
interface IMapRepository{
    suspend fun getBuildings(): Result<BuildingsResponse>
}