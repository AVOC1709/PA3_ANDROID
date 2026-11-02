package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.repository.EmpleadoRepository

// Caso de uso: eliminar un empleado por su DNI
class DeleteEmpleadoUseCase(private val repository: EmpleadoRepository) {
    operator fun invoke(dni: String) {
        repository.delete(dni)
    }
}