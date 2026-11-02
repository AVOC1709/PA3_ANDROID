package com.grupo1.gestionempleados.domain.usecase

import com.grupo1.gestionempleados.domain.model.Empleado
import com.grupo1.gestionempleados.domain.repository.EmpleadoRepository

// Caso de uso: listar todos los empleados
class GetEmpleadosUseCase(private val repository: EmpleadoRepository) {
    operator fun invoke(): List<Empleado> {
        return repository.getAll()
    }
}