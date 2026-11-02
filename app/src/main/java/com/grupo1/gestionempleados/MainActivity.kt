package com.grupo1.gestionempleados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.grupo1.gestionempleados.presentation.navigation.AppNavigation
import com.grupo1.gestionempleados.ui.theme.GestionEmpleadosTheme

// Punto de entrada de la app: aplica el tema y carga la navegación
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestionEmpleadosTheme {
                // Menú de navegación entre las vistas de Empleados y Cargos
                AppNavigation()
            }
        }
    }
}