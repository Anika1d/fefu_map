package mir.anika1d.handlenetworkexception.tools

import com.beust.klaxon.Klaxon
import mir.anika1d.handlenetworkexception.error.Error
data class ResponseError(
    val error: Error? = null,
    val status: String? = null,
) {
    companion object {
        fun fromJson(body: String?): ResponseError? {
            return if (body != null) {
                //     Gson().fromJson<ResponseError>(body, ResponseError::class.java)
                Klaxon().parse<ResponseError>(json = body)!!
                //  t
            } else
                null
        }
    }
}

