package com.example.contactapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColors = lightColorScheme(
    primary = BluePrimary,
    secondary = BlueSecondary,
    tertiary = BlueTertiary,
    background = BackgroundLight,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

private val DarkColors = darkColorScheme(
    primary = BluePrimary,
    secondary = BlueSecondary,
    background = BackgroundDark,
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.LightGray,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun ContactAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = Shapes(
            small = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
            medium = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
            large = androidx.compose.foundation.shape.RoundedCornerShape(20.dp)
        ),
        content = content
    )
}
