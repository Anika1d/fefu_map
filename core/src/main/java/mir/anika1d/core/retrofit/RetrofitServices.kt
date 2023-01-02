package mir.anika1d.core.retrofit

import mir.anika1d.core.data.response.BuildingsResponse
import retrofit2.http.GET
import mir.anika1d.handlenetworkexception.tools.result.Result
interface RetrofitServices:MapApi
interface MapApi {

    @GET("post/init")
   suspend fun getBuildings(): Result<BuildingsResponse>
}