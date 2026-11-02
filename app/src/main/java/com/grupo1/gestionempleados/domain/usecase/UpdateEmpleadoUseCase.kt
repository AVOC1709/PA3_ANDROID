package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.model.Empleado
import com.grupo1.gestionempleados.domain.repository.EmpleadoRepository

// Caso de uso: modificar los datos de un empleado existente
class UpdateEmpleadoUseCase(private val repository: EmpleadoRepository) {
    operator fun invoke(empleado: Empleado) {
        repository.updateOne(empleado)
    }
}