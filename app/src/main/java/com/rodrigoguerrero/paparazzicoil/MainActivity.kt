package com.rodrigoguerrero.paparazzicoil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.rodrigoguerrero.paparazzicoil.ui.theme.PaparazzicoilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaparazzicoilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PaparazzicoilTheme {
        Greeting("Android")
    }
}

@Composable
fun MyIcon(
    @DrawableRes defaultIconDrawableRes: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tintColor: Color = Color.Unspecified,
    url: String? = null,
    onError: ((Throwable) -> Unit)? = null,
) {
    Icon(
        modifier = modifier,
        tint = tintColor,
        painter = rememberAsyncImagePainter(
            model =  ImageRequest
                .Builder(LocalContext.current)
                .data(url)
                .error(defaultIconDrawableRes)
                .fallback(defaultIconDrawableRes)
                .build(),
            onError = { error ->
                onError?.invoke(error.result.throwable)
            },
        ),
        contentDescription = contentDescription,
    )
}
