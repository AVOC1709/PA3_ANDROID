package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.model.Empleado
import com.grupo1.gestionempleados.domain.repository.EmpleadoRepository

class AddEmpleadoUseCase(private val empleadoRepository: EmpleadoRepository) {
    operator fun invoke(empleado: Empleado) {
        empleadoRepository.add(empleado)
    }
}