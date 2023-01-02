package mir.anika1d.core.data.response

data class BuildingsResponse (
    val buildings: Map<String, Building>,
    val types: List<TypeElement>,
    val affiliations: List<Affiliation>,
    val icons: Map<String, String>,
    val roomIcons: Map<String, String>
)

data class Affiliation (
    val id: Long,
    val name: String
)

data class Building (
    val floor: List<Long>,
    val wing: List<String>,
    val basicFloor: Long,
    val icon: String,
    val coord: List<String>,
    val geojsonContour: GeoJsonContour,
    val contourBounds: ContourBounds
)

data class ContourBounds (
    val topLeft: List<String>,
    val bottomRight: List<String>
)

data class GeoJsonContour (
    val type: GeojsonContourType,
    val geometry: GeometryBackend,
)

data class GeometryBackend (
    val type: GeometryType,
    val coordinates: List<List<List<String>>>
)

enum class GeometryType {
    Polygon
}

enum class GeojsonContourType {
    Feature
}

data class TypeElement (
    val id: Long,
    val name: String,
    val color: String? = null
)