package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.model.Empleado
import com.grupo1.gestionempleados.domain.repository.EmpleadoRepository

// Caso de uso: registrar un empleado
class AddEmpleadoUseCase(private val repository: EmpleadoRepository) {
    operator fun invoke(empleado: Empleado) {
        repository.add(empleado)
    }
}