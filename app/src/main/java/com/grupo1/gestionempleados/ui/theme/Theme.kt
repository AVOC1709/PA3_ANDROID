package com.grupo1.gestionempleados.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

// Esquema de colores propio de la app en modo claro
private val AppLightColorScheme = lightColorScheme(
    primary = AzulPrincipal,
    onPrimary = Color.White,
    primaryContainer = AzulContenedor,
    secondary = VerdeAzulado,
    onSecondary = Color.White,
    secondaryContainer = VerdeAzuladoContenedor,
    tertiary = VerdeAzulado,
    background = FondoClaro,
    surface = FondoClaro,
    surfaceVariant = TarjetaClara,
    surfaceContainerHighest = TarjetaClara,
    surfaceContainer = TarjetaClara,
    error = RojoError
)

// Esquema de colores propio de la app en modo oscuro
private val AppDarkColorScheme = darkColorScheme(
    primary = AzulClaro,
    primaryContainer = AzulOscuroContenedor,
    secondary = VerdeAzuladoClaro,
    tertiary = VerdeAzuladoClaro,
    background = FondoOscuro,
    surface = FondoOscuro,
    surfaceVariant = TarjetaOscura,
    surfaceContainerHighest = TarjetaOscura,
    surfaceContainer = TarjetaOscura,
    error = RojoErrorClaro
)

@Composable
fun GestionEmpleadosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // Se desactiva para que la app use siempre sus propios colores
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> AppDarkColorScheme
        else -> AppLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}