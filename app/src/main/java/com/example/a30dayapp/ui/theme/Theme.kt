package com.example.a30dayapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkSaffron,
    onPrimary = DarkOnSaffron,
    primaryContainer = DarkSaffronContainer,
    onPrimaryContainer = DarkOnSaffronContainer,
    secondary = DarkTan,
    onSecondary = DarkOnTan,
    secondaryContainer = DarkTanContainer,
    onSecondaryContainer = DarkOnTanContainer,
    background = DarkCharcoal,
    onBackground = DarkOnCharcoal,
    surface = DarkCharcoal,
    onSurface = DarkOnCharcoal,
    surfaceVariant = DarkCardSurface,
    onSurfaceVariant = DarkOnCardSurface,
    outline = DarkOutline,
)

private val LightColorScheme = lightColorScheme(
    primary = LightSaffron,
    onPrimary = LightOnSaffron,
    primaryContainer = LightSaffronContainer,
    onPrimaryContainer = LightOnSaffronContainer,
    secondary = LightTan,
    onSecondary = LightOnTan,
    secondaryContainer = LightTanContainer,
    onSecondaryContainer = LightOnTanContainer,
    background = LightCream,
    onBackground = LightOnCream,
    surface = LightCream,
    onSurface = LightOnCream,
    surfaceVariant = LightCardSurface,
    onSurfaceVariant = LightOnCardSurface,
    outline = LightOutline,
)

/*
 * dynamicColor is deliberately absent. On Android 12+ it would repaint the app
 * with the user's wallpaper colours and destroy the brand.
 */
@Composable
fun GourmetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content,
    )
}
