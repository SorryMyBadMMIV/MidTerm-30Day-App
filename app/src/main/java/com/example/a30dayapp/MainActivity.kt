package com.example.a30dayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30dayapp.data.OpenedDaysStore
import com.example.a30dayapp.ui.GourmetApp
import com.example.a30dayapp.ui.theme.GourmetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val store = OpenedDaysStore(applicationContext)
        setContent {
            /*
             * The store is the source of truth, reloaded on every launch and
             * rotation, so plain remember is enough here — no rememberSaveable.
             */
            var openedDays by remember { mutableStateOf(store.load()) }
            GourmetTheme {
                GourmetApp(
                    openedDays = openedDays,
                    onDayOpened = { day ->
                        openedDays = openedDays + day
                        store.save(openedDays)
                    },
                )
            }
        }
    }
}

@Preview(name = "Dark", showBackground = true, showSystemUi = true)
@Composable
fun GourmetAppDarkPreview() {
    GourmetTheme(darkTheme = true) {
        GourmetApp(openedDays = setOf(1, 2))
    }
}

@Preview(name = "Light", showBackground = true, showSystemUi = true)
@Composable
fun GourmetAppLightPreview() {
    GourmetTheme(darkTheme = false) {
        GourmetApp(openedDays = setOf(1, 2))
    }
}
