package mir.anika1d.fefumap.composable.layuot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.dsl.cameraOptions

@Composable
fun MapBox(modifier: Modifier) {
    val mp:MapView
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        AndroidView(
            modifier = Modifier,
            factory = { context ->
                MapView(context).apply {
                    getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS) {
                        cameraOptions {
                            zoom(19.0)
                        }
                    }
                }
            }

        )
    }
}