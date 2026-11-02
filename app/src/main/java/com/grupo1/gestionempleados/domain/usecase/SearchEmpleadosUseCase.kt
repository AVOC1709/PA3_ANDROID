package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.model.Empleado
import com.grupo1.gestionempleados.domain.repository.EmpleadoRepository

// Caso de uso: buscar empleados por DNI, nombres o apellidos
class SearchEmpleadosUseCase(private val repository: EmpleadoRepository) {
    operator fun invoke(texto: String): List<Empleado> {
        return repository.search(texto)
    }
}
