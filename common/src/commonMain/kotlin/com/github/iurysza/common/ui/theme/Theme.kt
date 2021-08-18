package com.github.iurysza.vactrackerapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
  primary = ColorPrimary,
  primaryVariant = PrimaryVariant,
  secondary = SecondaryColor,
  background = BackgroundLight
)

private val LightColorPalette = lightColors(
  primary = ColorPrimary,
  primaryVariant = PrimaryVariant,
  background = BackgroundLight,
  secondary = SecondaryColor
)

@Composable
fun AndroidClientTheme(
  darkTheme: Boolean = false,
  content: @Composable() () -> Unit
) {
  val colors = if (darkTheme) {
    LightColorPalette
    // DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}