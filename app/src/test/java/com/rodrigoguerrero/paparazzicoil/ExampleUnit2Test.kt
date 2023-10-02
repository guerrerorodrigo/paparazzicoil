package com.rodrigoguerrero.paparazzicoil

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import coil.Coil
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.test.FakeImageLoaderEngine
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoilApi::class)
class ExampleUnit2Test {
    private val fakeUrl = "https://www.example.com/image.jpg"

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = PIXEL_5,
    )

    @Test
    fun addition_isCorrect() {
        paparazzi.snapshot {
            val engine = FakeImageLoaderEngine.Builder()
                .intercept("https://www.example.com/image.jpg", ColorDrawable(Color.RED))
                .intercept({ it is String && it.endsWith("test.png") }, ColorDrawable(Color.GREEN))
                .default(ColorDrawable(Color.BLUE))
                .build()
            val imageLoader = ImageLoader.Builder(paparazzi.context)
                .components { add(engine) }
                .build()
            Coil.setImageLoader(imageLoader)

            Box(Modifier.fillMaxSize()) {
                MyIcon(
                    url = fakeUrl,
                    defaultIconDrawableRes = R.drawable.ic_launcher_background,
                    contentDescription = "Test",
                )
            }
        }
    }
}