package com.grupo1.gestionempleados.presentation.empleado

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo1.gestionempleados.domain.model.Empleado

/*
 * Vista de mantenimiento de Empleados.
 * Permite registrar, modificar, eliminar, buscar y listar empleados.
 */
@Composable
fun EmpleadoScreen(
    modifier: Modifier = Modifier,
    viewModel: EmpleadoViewModel = viewModel()
) {
    // Campos del formulario
    var dni by remember { mutableStateOf("") }
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    var cargoSeleccionado by remember { mutableStateOf(viewModel.listaCargos.firstOrNull()) }
    var expanded by remember { mutableStateOf(false) }

    // true cuando se está modificando un empleado en lugar de registrar uno nuevo
    var editando by remember { mutableStateOf(false) }

    // Mensaje de error o de confirmación que se muestra bajo el formulario
    var mensaje by remember { mutableStateOf<String?>(null) }
    var esError by remember { mutableStateOf(false) }

    // Empleado que el usuario quiere eliminar (muestra el diálogo de confirmación)
    var empleadoAEliminar by remember { mutableStateOf<Empleado?>(null) }

    // Estado del scroll y foco, para subir al formulario y ocultar el teclado
    val listState = rememberLazyListState()
    val focusManager = LocalFocusManager.current

    // Deja el formulario vacío y en modo registro
    fun limpiarFormulario() {
        dni = ""; nombres = ""; apellidos = ""; sexo = ""; direccion = ""; correo = ""
        editando = false
        focusManager.clearFocus()
    }

    // Al editar o al mostrar un mensaje, sube al inicio para que se vea el formulario
    LaunchedEffect(editando, mensaje) {
        if (editando || mensaje != null) {
            listState.animateScrollToItem(0)
        }
    }

    // Revisa los datos del formulario; devuelve el error o null si todo está bien
    fun validarFormulario(): String? {
        return when {
            dni.isBlank() || nombres.isBlank() || apellidos.isBlank() || sexo.isBlank() ||
                direccion.isBlank() || correo.isBlank() -> "Completa todos los campos"
            dni.length != 8 || !dni.all { it.isDigit() } -> "El DNI debe tener 8 dígitos"
            !correo.contains("@") || !correo.contains(".") -> "Ingresa un correo válido"
            cargoSeleccionado == null -> "Selecciona un cargo"
            else -> null
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // Formulario de registro / modificación
        item {
            Text("Mantenimiento de Empleados", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = dni,
                onValueChange = { dni = it },
                label = { Text("DNI (Único)") },
                // El DNI identifica al empleado, por eso no se modifica al editar
                enabled = !editando,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = nombres,
                onValueChange = { nombres = it },
                label = { Text("Nombres") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = sexo,
                onValueChange = { sexo = it },
                label = { Text("Sexo") },
                modifier = Modifier.fillMaxWidth()
            )

            // Selector de cargo
            Box(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                OutlinedButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = cargoSeleccionado?.nombre ?: "Seleccionar Cargo")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    viewModel.listaCargos.forEach { cargo ->
                        DropdownMenuItem(
                            text = { Text(cargo.nombre) },
                            onClick = {
                                cargoSeleccionado = cargo
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val error = validarFormulario()
                    when {
                        error != null -> {
                            mensaje = error
                            esError = true
                        }
                        editando -> {
                            viewModel.actualizarEmpleado(
                                dni = dni.trim(),
                                nombres = nombres.trim(),
                                apellidos = apellidos.trim(),
                                sexo = sexo.trim(),
                                cargo = cargoSeleccionado!!,
                                direccion = direccion.trim(),
                                correo = correo.trim()
                            )
                            mensaje = "Empleado actualizado"
                            esError = false
                            limpiarFormulario()
                        }
                        viewModel.agregarEmpleado(
                            dni = dni.trim(),
                            nombres = nombres.trim(),
                            apellidos = apellidos.trim(),
                            sexo = sexo.trim(),
                            cargo = cargoSeleccionado!!,
                            direccion = direccion.trim(),
                            correo = correo.trim()
                        ) -> {
                            mensaje = "Empleado registrado"
                            esError = false
                            limpiarFormulario()
                        }
                        else -> {
                            // El DNI no debe repetirse
                            mensaje = "Ya existe un empleado con el DNI ${dni.trim()}"
                            esError = true
                        }
                    }
                },
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
            ) {
                Text(if (editando) "Actualizar Empleado" else "Registrar Empleado")
            }

            // Solo aparece en modo edición
            if (editando) {
                OutlinedButton(
                    onClick = {
                        limpiarFormulario()
                        mensaje = null
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancelar")
                }
            }

            mensaje?.let {
                Text(
                    text = it,
                    color = if (esError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Lista de Empleados", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Buscador: filtra la lista mientras se escribe
            OutlinedTextField(
                value = viewModel.textoBusqueda,
                onValueChange = { viewModel.buscarEmpleados(it) },
                label = { Text("Buscar por DNI, nombres o apellidos") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (viewModel.listaEmpleados.isEmpty()) {
                Text("No se encontraron empleados", modifier = Modifier.padding(vertical = 8.dp))
            }
        }

        // Una tarjeta por cada empleado
        items(viewModel.listaEmpleados, key = { it.dni }) { empleado ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "${empleado.nombres} ${empleado.apellidos}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(text = "DNI: ${empleado.dni} | Sexo: ${empleado.sexo}")
                    Text(text = "Cargo: ${empleado.cargo.nombre}")
                    Text(text = "Correo: ${empleado.correo}")
                    Text(text = "Dirección: ${empleado.direccion}")

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Carga los datos del empleado en el formulario para modificarlos
                        Button(onClick = {
                            dni = empleado.dni
                            nombres = empleado.nombres
                            apellidos = empleado.apellidos
                            sexo = empleado.sexo
                            cargoSeleccionado = empleado.cargo
                            direccion = empleado.direccion
                            correo = empleado.correo
                            editando = true
                            mensaje = null
                        }) {
                            Text("Editar")
                        }
                        Button(
                            onClick = { empleadoAEliminar = empleado },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }

    // Diálogo para confirmar antes de eliminar
    empleadoAEliminar?.let { empleado ->
        AlertDialog(
            onDismissRequest = { empleadoAEliminar = null },
            title = { Text("Eliminar empleado") },
            text = { Text("¿Seguro que deseas eliminar a ${empleado.nombres} ${empleado.apellidos}?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.eliminarEmpleado(empleado.dni)
                    if (dni == empleado.dni) limpiarFormulario()
                    mensaje = "Empleado eliminado"
                    esError = false
                    empleadoAEliminar = null
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { empleadoAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
