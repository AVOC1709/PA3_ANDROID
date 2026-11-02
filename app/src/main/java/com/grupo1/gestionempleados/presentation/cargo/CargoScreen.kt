package com.grupo1.gestionempleados.presentation.cargo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo1.gestionempleados.domain.model.Cargo

/*
 * Vista de mantenimiento de Cargos.
 * Permite registrar, modificar, eliminar, buscar y listar cargos.
 */
@Composable
fun CargoScreen(
    modifier: Modifier = Modifier,
    viewModel: CargoViewModel = viewModel()
) {
    // Campos del formulario
    var codigo by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }

    // true cuando se está modificando un cargo en lugar de registrar uno nuevo
    var editando by remember { mutableStateOf(false) }

    // Mensaje de error o de confirmación que se muestra bajo el formulario
    var mensaje by remember { mutableStateOf<String?>(null) }
    var esError by remember { mutableStateOf(false) }

    // Cargo que el usuario quiere eliminar (muestra el diálogo de confirmación)
    var cargoAEliminar by remember { mutableStateOf<Cargo?>(null) }

    // Estado del scroll y foco, para subir al formulario y ocultar el teclado
    val listState = rememberLazyListState()
    val focusManager = LocalFocusManager.current

    // Deja el formulario vacío y en modo registro
    fun limpiarFormulario() {
        codigo = ""
        nombre = ""
        editando = false
        focusManager.clearFocus()
    }

    // Al editar o al mostrar un mensaje, sube al inicio para que se vea el formulario
    LaunchedEffect(editando, mensaje) {
        if (editando || mensaje != null) {
            listState.animateScrollToItem(0)
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
            Text("Mantenimiento de Cargos", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = codigo,
                onValueChange = { codigo = it.uppercase() },
                label = { Text("Código (Único)") },
                // El código identifica al cargo, por eso no se modifica al editar
                enabled = !editando,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del cargo") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    // Validaciones antes de guardar
                    when {
                        codigo.isBlank() || nombre.isBlank() -> {
                            mensaje = "Completa el código y el nombre del cargo"
                            esError = true
                        }
                        editando -> {
                            viewModel.actualizarCargo(codigo.trim(), nombre.trim())
                            mensaje = "Cargo actualizado"
                            esError = false
                            limpiarFormulario()
                        }
                        viewModel.agregarCargo(codigo.trim(), nombre.trim()) -> {
                            mensaje = "Cargo registrado"
                            esError = false
                            limpiarFormulario()
                        }
                        else -> {
                            mensaje = "Ya existe un cargo con el código ${codigo.trim()}"
                            esError = true
                        }
                    }
                },
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
            ) {
                Text(if (editando) "Actualizar Cargo" else "Registrar Cargo")
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
            Text("Lista de Cargos", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Buscador: filtra la lista mientras se escribe
            OutlinedTextField(
                value = viewModel.textoBusqueda,
                onValueChange = { viewModel.buscarCargos(it) },
                label = { Text("Buscar por código o nombre") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (viewModel.listaCargos.isEmpty()) {
                Text("No se encontraron cargos", modifier = Modifier.padding(vertical = 8.dp))
            }
        }

        // Una tarjeta por cada cargo
        items(viewModel.listaCargos, key = { it.codigo }) { cargo ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = cargo.nombre, style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Código: ${cargo.codigo}")

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Carga los datos del cargo en el formulario para modificarlos
                        Button(onClick = {
                            codigo = cargo.codigo
                            nombre = cargo.nombre
                            editando = true
                            mensaje = null
                        }) {
                            Text("Editar")
                        }
                        Button(
                            onClick = { cargoAEliminar = cargo },
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
    cargoAEliminar?.let { cargo ->
        AlertDialog(
            onDismissRequest = { cargoAEliminar = null },
            title = { Text("Eliminar cargo") },
            text = { Text("¿Seguro que deseas eliminar el cargo ${cargo.nombre}?") },
            confirmButton = {
                TextButton(onClick = {
                    if (viewModel.eliminarCargo(cargo.codigo)) {
                        mensaje = "Cargo eliminado"
                        esError = false
                        if (codigo == cargo.codigo) limpiarFormulario()
                    } else {
                        mensaje = "No se puede eliminar: hay empleados con el cargo ${cargo.nombre}"
                        esError = true
                    }
                    cargoAEliminar = null
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { cargoAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
