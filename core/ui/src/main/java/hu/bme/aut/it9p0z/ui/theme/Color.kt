package hu.bme.aut.it9p0z.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val md_theme_light_primary = Color(0xFF835400)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFFFDDB5)
val md_theme_light_onPrimaryContainer = Color(0xFF2A1800)
val md_theme_light_secondary = Color(0xFF705B40)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFFBDEBC)
val md_theme_light_onSecondaryContainer = Color(0xFF271905)
val md_theme_light_tertiary = Color(0xFF52643F)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFD5EABA)
val md_theme_light_onTertiaryContainer = Color(0xFF111F03)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFFFBFF)
val md_theme_light_onBackground = Color(0xFF1F1B16)
val md_theme_light_surface = Color(0xFFFFFBFF)
val md_theme_light_onSurface = Color(0xFF1F1B16)
val md_theme_light_surfaceVariant = Color(0xFFF0E0D0)
val md_theme_light_onSurfaceVariant = Color(0xFF4F4539)
val md_theme_light_outline = Color(0xFF817567)
val md_theme_light_inverseOnSurface = Color(0xFFF9EFE7)
val md_theme_light_inverseSurface = Color(0xFF35302A)
val md_theme_light_inversePrimary = Color(0xFFFFB956)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF835400)

val md_theme_dark_primary = Color(0xFFFFB956)
val md_theme_dark_onPrimary = Color(0xFF452B00)
val md_theme_dark_primaryContainer = Color(0xFF633F00)
val md_theme_dark_onPrimaryContainer = Color(0xFFFFDDB5)
val md_theme_dark_secondary = Color(0xFFDEC2A2)
val md_theme_dark_onSecondary = Color(0xFF3E2D16)
val md_theme_dark_secondaryContainer = Color(0xFF57432B)
val md_theme_dark_onSecondaryContainer = Color(0xFFFBDEBC)
val md_theme_dark_tertiary = Color(0xFFB9CDA0)
val md_theme_dark_onTertiary = Color(0xFF253515)
val md_theme_dark_tertiaryContainer = Color(0xFF3B4C29)
val md_theme_dark_onTertiaryContainer = Color(0xFFD5EABA)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF1F1B16)
val md_theme_dark_onBackground = Color(0xFFEAE1D9)
val md_theme_dark_surface = Color(0xFF1F1B16)
val md_theme_dark_onSurface = Color(0xFFEAE1D9)
val md_theme_dark_surfaceVariant = Color(0xFF4F4539)
val md_theme_dark_onSurfaceVariant = Color(0xFFD3C4B4)
val md_theme_dark_outline = Color(0xFF9C8F80)
val md_theme_dark_inverseOnSurface = Color(0xFF1F1B16)
val md_theme_dark_inverseSurface = Color(0xFFEAE1D9)
val md_theme_dark_inversePrimary = Color(0xFF835400)
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFFFFB956)


val seed = Color(0xFF8F6528)

val ColorScheme.satisfiedColor
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF6BBB6E)
                        else Color(0xFF267A2E)

val ColorScheme.idleColor
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFA4A5A4)
    else Color(0xFF7C7E7C)

val ColorScheme.swipeToEdit
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFCF8402)
                        else Color(0xFFFFC700)

val ColorScheme.onSwipeToEdit
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFFFE4B5)
                        else Color(0xFFFFEECF)

val ColorScheme.swipeToDelete
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF690005)
                        else Color(0xFF93000A)

val ColorScheme.onSwipeToDelete
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFFFDDDF)
                        else Color(0xFFE6CACC)

val ColorScheme.listItemBackgroundThreshold
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFB8B7B7)
                        else Color(0xFF3D3D3D)

val ColorScheme.warning
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFCF8402)
                        else Color(0xFFFFC700)