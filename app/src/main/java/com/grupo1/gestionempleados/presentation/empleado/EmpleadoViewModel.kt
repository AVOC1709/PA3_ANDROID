package com.grupo1.gestionempleados.presentation.empleado

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.grupo1.gestionempleados.data.datasource.EmpleadoDataSource
import com.grupo1.gestionempleados.data.repository.EmpleadoRepositoryImp
import com.grupo1.gestionempleados.domain.model.Cargo
import com.grupo1.gestionempleados.domain.model.Empleado
import com.grupo1.gestionempleados.domain.usecase.AddEmpleadoUseCase
import com.grupo1.gestionempleados.domain.usecase.DeleteEmpleadoUseCase
import com.grupo1.gestionempleados.domain.usecase.GetEmpleadoByDniUseCase
import com.grupo1.gestionempleados.domain.usecase.GetEmpleadosUseCase
import com.grupo1.gestionempleados.domain.usecase.SearchEmpleadosUseCase
import com.grupo1.gestionempleados.domain.usecase.UpdateEmpleadoUseCase

/*
 * ViewModel de la vista de Empleados.
 * Conecta la pantalla con los casos de uso y guarda el estado de la lista.
 */
class  EmpleadoViewModel : ViewModel() {
    private val repository = EmpleadoRepositoryImp()
    private val getEmpleadosUseCase = GetEmpleadosUseCase(repository)
    private val addEmpleadoUseCase = AddEmpleadoUseCase(repository)
    private val deleteEmpleadoUseCase = DeleteEmpleadoUseCase(repository)
    private val updateEmpleadoUseCase = UpdateEmpleadoUseCase(repository)
    private val searchEmpleadosUseCase = SearchEmpleadosUseCase(repository)
    private val getEmpleadoByDniUseCase = GetEmpleadoByDniUseCase(repository)

    // Lista que se muestra en pantalla (Compose se actualiza cuando cambia)
    var listaEmpleados = mutableStateListOf<Empleado>()
        private set

    var listaCargos = EmpleadoDataSource.cargos
        private set

    // Texto escrito en el buscador; vacío muestra todos los empleados
    var textoBusqueda by mutableStateOf("")
        private set

    init {
        cargarEmpleados()
    }

    // Carga la lista completa o la filtrada si hay texto de búsqueda
    fun cargarEmpleados() {
        listaEmpleados.clear()
        if (textoBusqueda.isBlank()) {
            listaEmpleados.addAll(getEmpleadosUseCase())
        } else {
            listaEmpleados.addAll(searchEmpleadosUseCase(textoBusqueda.trim()))
        }
    }

    // Devuelve true si se registró, o false si el DNI ya existía
    fun agregarEmpleado(dni: String, nombres: String, apellidos: String, sexo: String, cargo: Cargo, direccion: String, correo: String): Boolean {
        if (existeDni(dni)) {
            return false
        }
        val nuevoEmpleado = Empleado(dni, nombres, apellidos, sexo, cargo, direccion, correo)
        addEmpleadoUseCase(nuevoEmpleado)
        cargarEmpleados()
        return true
    }

    // Modifica un empleado existente (el DNI no cambia porque lo identifica)
    fun actualizarEmpleado(dni: String, nombres: String, apellidos: String, sexo: String, cargo: Cargo, direccion: String, correo: String) {
        val empleado = Empleado(dni, nombres, apellidos, sexo, cargo, direccion, correo)
        updateEmpleadoUseCase(empleado)
        cargarEmpleados()
    }

    fun eliminarEmpleado(dni: String) {
        deleteEmpleadoUseCase(dni)
        cargarEmpleados()
    }

    // Se llama cada vez que el usuario escribe en el buscador
    fun buscarEmpleados(texto: String) {
        textoBusqueda = texto
        cargarEmpleados()
    }

    fun existeDni(dni: String): Boolean {
        return getEmpleadoByDniUseCase(dni) != null
    }
}
