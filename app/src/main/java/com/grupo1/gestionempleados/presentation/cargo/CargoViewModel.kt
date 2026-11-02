package com.grupo1.gestionempleados.presentation.cargo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.grupo1.gestionempleados.data.repository.CargoRepositoryImp
import com.grupo1.gestionempleados.domain.model.Cargo
import com.grupo1.gestionempleados.domain.usecase.AddCargoUseCase
import com.grupo1.gestionempleados.domain.usecase.DeleteCargoUseCase
import com.grupo1.gestionempleados.domain.usecase.GetCargosUseCase
import com.grupo1.gestionempleados.domain.usecase.SearchCargosUseCase
import com.grupo1.gestionempleados.domain.usecase.UpdateCargoUseCase

/*
 * ViewModel de la vista de Cargos.
 * Conecta la pantalla con los casos de uso y guarda el estado de la lista.
 */
class CargoViewModel : ViewModel() {
    private val repository = CargoRepositoryImp()
    private val getCargosUseCase = GetCargosUseCase(repository)
    private val addCargoUseCase = AddCargoUseCase(repository)
    private val updateCargoUseCase = UpdateCargoUseCase(repository)
    private val deleteCargoUseCase = DeleteCargoUseCase(repository)
    private val searchCargosUseCase = SearchCargosUseCase(repository)

    // Lista que se muestra en pantalla (Compose se actualiza cuando cambia)
    var listaCargos = mutableStateListOf<Cargo>()
        private set

    // Texto escrito en el buscador; vacío muestra todos los cargos
    var textoBusqueda by mutableStateOf("")
        private set

    init {
        cargarCargos()
    }

    // Carga la lista completa o la filtrada si hay texto de búsqueda
    fun cargarCargos() {
        listaCargos.clear()
        if (textoBusqueda.isBlank()) {
            listaCargos.addAll(getCargosUseCase())
        } else {
            listaCargos.addAll(searchCargosUseCase(textoBusqueda.trim()))
        }
    }

    // Devuelve true si se registró, o false si el código ya existía
    fun agregarCargo(codigo: String, nombre: String): Boolean {
        val agregado = addCargoUseCase(Cargo(codigo = codigo, nombre = nombre))
        cargarCargos()
        return agregado
    }

    fun actualizarCargo(codigo: String, nombre: String) {
        updateCargoUseCase(Cargo(codigo = codigo, nombre = nombre))
        cargarCargos()
    }

    // Devuelve false si no se pudo eliminar porque hay empleados con ese cargo
    fun eliminarCargo(codigo: String): Boolean {
        val eliminado = deleteCargoUseCase(codigo)
        cargarCargos()
        return eliminado
    }

    // Se llama cada vez que el usuario escribe en el buscador
    fun buscarCargos(texto: String) {
        textoBusqueda = texto
        cargarCargos()
    }
}
