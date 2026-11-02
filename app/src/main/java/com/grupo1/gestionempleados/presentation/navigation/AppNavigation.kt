package com.grupo1.gestionempleados.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grupo1.gestionempleados.presentation.cargo.CargoScreen
import com.grupo1.gestionempleados.presentation.empleado.EmpleadoScreen

/*
 * Rutas de las vistas de la aplicación.
 * Cada opción tiene su ruta, el texto del menú y su ícono.
 */
sealed class Pantalla(val ruta: String, val titulo: String, val icono: ImageVector) {
    data object Empleados : Pantalla("empleados", "Empleados", Icons.Default.People)
    data object Cargos : Pantalla("cargos", "Cargos", Icons.Default.Work)
}

// Opciones que aparecen en el menú de navegación inferior
private val opcionesMenu = listOf(Pantalla.Empleados, Pantalla.Cargos)

/*
 * Estructura principal de la app: menú de navegación inferior
 * y el contenedor que muestra la vista seleccionada.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val destinoActual = backStackEntry?.destination

                opcionesMenu.forEach { pantalla ->
                    NavigationBarItem(
                        selected = destinoActual?.hierarchy?.any { it.route == pantalla.ruta } == true,
                        onClick = {
                            navController.navigate(pantalla.ruta) {
                                // Evita acumular pantallas repetidas al cambiar de opción
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(pantalla.icono, contentDescription = pantalla.titulo) },
                        label = { Text(pantalla.titulo) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // innerPadding evita que el contenido quede bajo la barra de estado o el menú
        NavHost(
            navController = navController,
            startDestination = Pantalla.Empleados.ruta,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Pantalla.Empleados.ruta) {
                EmpleadoScreen()
            }
            composable(Pantalla.Cargos.ruta) {
                CargoScreen()
            }
        }
    }
}
