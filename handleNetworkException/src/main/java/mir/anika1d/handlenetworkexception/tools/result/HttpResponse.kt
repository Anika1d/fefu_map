package mir.anika1d.handlenetworkexception.tools.result


interface HttpResponse {
    val statusCode: Int
    val statusMessage: String?
    val url: String?
}